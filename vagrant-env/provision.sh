[[ -z $(which lsof) || -z $(which wget) ]] && yum install -y java-1.7.0-openjdk.x86_64 lsof wget

# Set up and run WireMock
[[ -z $(grep "wiremock" /etc/passwd) ]] && useradd wiremock
[[ -d /usr/lib/wiremock ]] || mkdir -p /usr/lib/wiremock
chown -R wiremock:wiremock /usr/lib/wiremock
cd /usr/lib/wiremock
[[ -f wiremock-1.36-standalone.jar ]] || wget http://central.maven.org/maven2/com/github/tomakehurst/wiremock/1.36/wiremock-1.36-standalone.jar
[[ -z $(pgrep -u wiremock java) ]] && nohup su -c "java -jar wiremock-1.36-standalone.jar --verbose" wiremock &


# Run Breakbox
pkill -u root java
cd /usr/lib/breakbox
nohup java -jar $(ls *standalone*.jar) &