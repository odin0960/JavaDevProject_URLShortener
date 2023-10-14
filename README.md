Environment variables for project:

PORT=9999;

SECURITY_USER=user;
SECURITY_PASS=default;

LOCAL_DB_LINK=jdbc:h2:mem:shortlink;
LOCAL_DB_USERNAME=sa;
LOCAL_DB_PASSWORD=password;

PROD_DB_LINK=jdbc:postgresql://localhost:5432/shortlink_db;
PROD_DB_USERNAME=postgres;
PROD_DB_PASSWORD=postgres;

TOKEN_LENGTH=7;
VALIDITY_PERIOD=72;
APP_VERSION=v.1.0;