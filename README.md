# 🌍 Greek Travel Web App

A web-based travel booking platform developed using Java, JSP, and Servlets. This project follows the MVC (Model-View-Controller) design pattern and connects to a MySQL database to manage users, trips, and bookings.

---

## ✨ Features
- User registration and login
- Browse trips and accommodations
- Booking system for users
- Admin panel for managing listings and content

---

## 🛠️ Technologies Used
- Java
- JSP & Servlets
- MySQL
- HTML, CSS, JavaScript (with jQuery UI)
- Apache Tomcat (recommended application server)

---

## 📁 Project Structure
```
Greek-Travel-Web-App/
├── java/                 # Java source code (controllers, models, DAOs)
├── web/                  # Web resources
│   ├── css/              # Stylesheets
│   ├── js/               # JavaScript files
│   ├── jqueryui/         # jQuery UI assets
│   ├── WEB-INF/          # web.xml and JSPs
│   └── META-INF/         # Metadata
├── greektravel.mwb       # MySQL Workbench schema
├── README.md             # Project documentation
```

---

## 🧰 Prerequisites
- Java JDK 8 or later
- Apache Tomcat 9 or later
- MySQL Server
- Maven or IntelliJ IDEA (recommended)

---

## 🗄️ Database Setup
1. Open `greektravel.mwb` using MySQL Workbench.
2. Export or run the schema to generate the required tables.
3. Update database credentials in the appropriate DAO or utility class (typically inside the `jpautils` or `dao` packages).

---

## ▶️ How to Run the Application

### Option 1: Run with IntelliJ IDEA
1. Open the project as a Maven or standard Java project.
2. Configure a new Tomcat Server: `Run > Edit Configurations > Add New Configuration > Tomcat Server`.
3. Set the deployment to use `war exploded` from the `web` folder.
4. Start the Tomcat configuration.
5. Open a browser and go to `http://localhost:8080`.

### Option 2: Manual Deployment to Tomcat
1. Package the project to generate a `.war` file.
2. Copy the `.war` file into the `webapps/` directory of your Tomcat installation.
3. Start the Tomcat server.
4. Access the app via `http://localhost:8080/Greek-Travel-Web-App`.

---

## 🔐 Demo Credentials *(if applicable)*
> Add default login credentials here (e.g., admin/admin123).

---

## 👨‍💻 Author
This project was originally written in Greek. English README and refactoring provided on request.

---

## 🤝 Contributions
Contributions and suggestions are welcome! Feel free to open issues or submit pull requests to improve the project.
