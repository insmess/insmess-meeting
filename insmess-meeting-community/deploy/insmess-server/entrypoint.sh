#!/bin/sh
sed -i 's/\${KMS_HOST}/'"$KMS_HOST"'/' /home/application.properties
sed -i 's/\${DOMAIN_OR_PUBLIC_IP}/'"$DOMAIN_OR_PUBLIC_IP"'/' /home/.env
java -jar openvidu-server-2.25.0.jar