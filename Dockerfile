# Собираем образ из этого файла
# sudo docker build -t custom-jenkins-maven-firefox-last .
# Запускаем образ и монтируем директорию чтобы не кадый раз все перевыкачивать
# sudo docker run -p 8080:8080 -p 50000:50000 --restart=on-failure   -v /home/acer/Documents/Jenkins:/var/jenkins_home   custom-jenkins-maven-firefox-last
# Проверяем что все запустилось и вытаскиваем пароль при первом запусе
# sudo docker ps
# При неоходимости грохаем
# sudo docker stop 05b1c51ce326
# sudo docker rm 05b1c51ce326
# Влезть в контейнер который уже запущен
# sudo docker exec -it 05b1c51ce326 mvn -version

FROM jenkins/jenkins:lts-jdk17

# Переходим на пользователя root для установки системных пакетов
USER root

# Обновляем пакеты и устанавливаем необходимые зависимости:
# - xvfb, libgtk-3-0, libdbus-glib-1-2, libxt6, wget, bzip2 (для корректной работы wget, даже если архив не bzip2) 
RUN apt-get update && \
    apt-get install -y xvfb libgtk-3-0 libdbus-glib-1-2 libxt6 wget bzip2 firefox-esr && \
    rm -rf /var/lib/apt/lists/*

############################################################
# Установка Maven 3.9.9 вручную
############################################################
ENV MAVEN_VERSION=3.9.9
RUN wget -O /tmp/apache-maven.tar.gz "https://dlcdn.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz" && \
    tar xzf /tmp/apache-maven.tar.gz -C /opt && \
    rm /tmp/apache-maven.tar.gz && \
    ln -s /opt/apache-maven-${MAVEN_VERSION} /opt/maven
ENV M2_HOME=/opt/maven
ENV PATH=$M2_HOME/bin:$PATH


############################################################
# Установка geckodriver (версия 0.36.0, можно адаптировать по необходимости)
############################################################
ENV GECKODRIVER_VERSION=0.36.0
RUN wget --quiet -O /tmp/geckodriver.tar.gz "https://github.com/mozilla/geckodriver/releases/download/v$GECKODRIVER_VERSION/geckodriver-v$GECKODRIVER_VERSION-linux64.tar.gz" && \
    tar -xzf /tmp/geckodriver.tar.gz -C /usr/local/bin && \
    rm /tmp/geckodriver.tar.gz && \
    chmod +x /usr/local/bin/geckodriver

# Возвращаемся к пользователю jenkins
USER jenkins

