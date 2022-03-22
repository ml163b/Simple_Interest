# Simple_Interest Service
calculateand generates the list of payments of the simple interest of a credit that must be paid in n terms and in weekly

# Notes
1. Once the project is cloned do a  mvn clean test spring-boot:run
2. You can use next command to test healt Endpoint; curl "http://localhost:8080/actuator/health" or just put URL in browser.
3. The methods to test the service  are:
    * POST http://localhost:8080/simpleInterest/payments (to calculate terms)
      Exaple Request: {  
                        "amount": 2000.0,   
                        "terms": 5,   
                        "rate": 10      
                      } 
   * GET http://localhost:8080/simpleInterest/payments (to get all petitions made)
   * DELETE http://localhost:8080/simpleInterest/payments (to delete all petitions made)
5. You see the DB at http://localhost:8080/h2-console bd:"mem:aplazodb" user:"root", password: (empty)

# Create Docker Image
mvn package 
docker build -t docker/simple_interest-service:v0.1

# Run Docker Image
docker run -p 8080:8080 aplazo/simple_interest-service:v0.1

