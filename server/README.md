# Serving the app
To serve this app on the RaspberryPi as a web server, do the following:

1. Build the WAR file with Maven: `mvn clean install`
    1. To increase build time:
        1. use the `-DskipTests` flag to skip unit tests
        2. use the `-Pdev` to use the dev Maven profile, this will skip the building of the frontend, so only use this if you have built the frontend previously.
    
2. Copy the WAR file to your raspberry pi
    1. `scp miniGolf-0.0.1-SNAPSHOT.war <username>@<ip-address>:/var/minigolfApp`
        1. The `<username>` is the username you use to log in to your raspberry pi
        2. the `<ip-address>` is the ip address of your raspberry pi on your local network
        3. You will be asked for a password, this is the pw associated with your chosen user on the raspberry pi.
        4. Example `scp miniGolf-0.0.1-SNAPSHOT.war rhysjones@192.168.0.54:/var/minigolfApp`
        5. Note, you might need to create the directory `/var/minigolfApp`

3. Set up Raspberry Pi as a web server, there are two options
    1. Use **Apache Web Server** to serve to your local network
        1. A good article for this: https://medium.com/geekculture/turn-your-raspberry-pi-into-a-server-to-run-your-java-spring-mvc-app-862214279587
    2. Use **NGrok** to serve your app over the internet at a random URL - you can do this for freefor 2 hours at a time
        1. https://ngrok.com/
    
4. Install Java on your Raspberry Pi

5. Install and set up PostgreSQL on your Raspberry Pi
    1. Install with `sudo apt install postgresql`
    2. Set up as per this article: https://pimylifeup.com/raspberry-pi-postgresql/
    3. Create a database called `minigolf`
    4. By default, PSQL should run on localhost:5432
   
6. Create script to run the app on start up as a service
    1. Copy the start up script as per this article: https://medium.com/geekculture/turn-your-raspberry-pi-into-a-server-to-run-your-java-spring-mvc-app-862214279587
    2. Name the service `minigolf.service`
    3. In the service, add a new line `EnvironmentFile=/etc/systemd/system/minigolf.conf` - this points the service to a config file containing your env vars
    4. Create the config file in `/etc/systemd/system` with `touch minigolf.conf`
    5. in `minigolf.conf` add in your env vars for the DB config. Change the values of the env vars to be correct for your set up.
       * DB_HOST=localhost
       * DB_PORT=5432
       * DB_NAME=minigolf
       * DB_USERNAME=rhysjones
       * DB_PASSWORD=password
       * SPRING_PROFILES_ACTIVE=prod

4. Run your app
   1. `sudo systemctl enable minigolf.service`
   2. `sudo systemctl start minigolf`
   3. The app should start every time you start up your Raspberry Pi