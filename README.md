# JAVA UI - JavaFX, Guice DI

This project is a boiler plate JavaFX WebUI combined with a Guice Messenger.  

### Current Structure (Reduced Boilerplate - 3 files → 2 files):
- **Main.java**: Application entry point
- **UIApp.java**: JavaFX UI + Inner classes (MessageService, Product, Cart, ProductDAO)
- `AppModule.java`, `WeezeGuice.java`, `MainInstance.java`: REMOVED

### Future Database Integration Notes:

1. **Database Setup Requirements:**
   - Add database dependency (PostgreSQL/MySQL)
   - Create connection pool configuration
   - Set up database schema for products/cart tables

2. **Implementation Steps:**
   ```java
   // ProductDAO.java - implement actual DB queries
   public class ProductDAO {
       private final ConnectionPool connectionPool;
       
       public Product getProduct(String name) {
           // DB query: SELECT * FROM products WHERE name = ?
           // return result mapping to Product object
       }
       
       public void addToCart(String productName) {
           // DB query: INSERT INTO cart_items (product_name, quantity) VALUES (?, 1)
       }
   }
   
   // CartModule.java - configure bindings for DI
   public class CartModule extends AbstractModule {
       @Override
       protected void configure() {
           bind(ConnectionPool.class).toInstance(new ConnectionPool(...));
           bind(ProductDAO.class).to(ProductDAO.class);
           bind(Cart.class).to(Cart.class);
       }
   }
   ```

3. **DI Configuration Changes:**
   - Modify UIApp.java to use CartModule instead of inline injector
   - Change from per-request to singleton/scoped binding for DAOs
   - Add lifecycle management for database connections

4. **Database Schema Suggestion:**
   ```sql
   CREATE TABLE products (
       id SERIAL PRIMARY KEY,
       name VARCHAR(100) UNIQUE NOT NULL,
       price DECIMAL(10,2) NOT NULL
   );
   
   CREATE TABLE cart_items (
       id SERIAL PRIMARY KEY,
       product_id INTEGER REFERENCES products(id),
       quantity INTEGER DEFAULT 1
   );
   ```

5. **Dependencies to Add:**
   ```gradle
   // In build.gradle
   dependencies {
       implementation 'org.postgresql:postgresql:42.7.4' // PostgreSQL driver
       implementation 'com.zaxxer:HikariCP:5.1.0'        // Connection pool
   }
   ```

```log
┌─────────────────┐     ┌──────────────────┐     ┌─────────────────┐
│   UIApp.java    │────▶│  CartModule.java │────▶│ ProductDAO.java │
│  (inject)       │     │  (binds DAOs)    │     │ (db operations) │
└─────────────────┘     └──────────────────┘     └─────────────────┘
                              │
                              ▼
                      ┌──────────────────┐
                      │ CartRepository   │
                      │ (domain logic)   │
                      └──────────────────┘
```
#
## Install JavaFX
```bash
export PATH_TO_FX='/Library/Java/JavaFx/javafx-sdk-25.0.4/lib'

# Creates a Main.class File
javac --module-path $PATH_TO_FX --add-modules javafx.controls src/main/java/com/example/Main.java

### Guice Dependency memory override
java --sun-misc-unsafe-memory-access=allow

# Run
java --module-path $PATH_TO_FX --add-modules javafx.controls src/main/java/com/example/Main.java 
```

## Gradle - Run the project
```bash
brew install gradle
# ** use previously installed openjdk25 instead of v27 **

# clear cache to download new dependencies
rm -rf $HOME/.gradle/caches/
gradle clean build --warning-mode all

# publishes to Develocity (full report)
gradle run --scan

# Adds gradlew files
gradle wrapper
# Verify connections to JVM
./gradlew --version
./gradlew javaToolChains

./gradlew run
# Ctrl + c 
```