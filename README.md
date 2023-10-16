Environment variables for project:

PORT=9999;

SECURITY_USER=user;
SECURITY_PASS=default;

LOCAL_DB_LINK=jdbc:h2:mem:shortlink;DATABASE_TO_UPPER=false;
LOCAL_DB_USERNAME=sa;
LOCAL_DB_PASSWORD=password;

PROD_DB_LINK=jdbc:postgresql://shortlink-1.cjfbwpo4wfwq.eu-north-1.rds.amazonaws.com:5432/shortlink_db;
PROD_DB_USERNAME=userprod;
PROD_DB_PASSWORD=postgrespass;

APP_VERSION=v.1.0;
TOKEN_LENGTH=5;
VALIDITY_PERIOD=72;
CACHE_SIZE=1000;
CACHE_PERIOD_MINUTES=15;