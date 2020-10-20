#
# Deploy stage: deploying app onto the WildFly image
#
FROM jboss/wildfly
COPY --from=0 /home/app/target/webapp.war /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080