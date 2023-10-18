SWAGGER http://localhost:9999/swagger-ui/index.html

Environment variables for project:<br>
PORT=9999;<br>

SECURITY_USER=user;<br>
SECURITY_PASS=default;<br>

LOCAL_DB_LINK=jdbc:h2:mem:shortlink;DATABASE_TO_UPPER=false;<br>
LOCAL_DB_USERNAME=sa;<br>
LOCAL_DB_PASSWORD=password;<br>

PROD_DB_LINK=jdbc:postgresql://shortlink-1.cjfbwpo4wfwq.eu-north-1.rds.amazonaws.com:5432/shortlink_db;<br>
PROD_DB_USERNAME=userprod;<br>
PROD_DB_PASSWORD=postgrespass;<br>

APP_VERSION=v.1.0;<br>
TOKEN_LENGTH=5;<br>
VALIDITY_PERIOD=72;<br>
CACHE_SIZE=1000;<br>
CACHE_PERIOD_MINUTES=15;<br>
