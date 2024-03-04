# Sử dụng một hình ảnh JDK để xây dựng ứng dụng
FROM openjdk:11-jdk-slim AS build

# Sao chép mã nguồn vào thư mục làm việc của container
COPY . /usr/src/app

# Thiết lập thư mục làm việc
WORKDIR /usr/src/app
COPY mvnw ./
COPY .mvn ./.mvn

# Biên dịch ứng dụng bằng Maven
RUN ./mvnw clean package

# Sử dụng hình ảnh OpenJDK để chạy ứng dụng
FROM openjdk:11-jre-slim

# Sao chép tệp JAR đã được xây dựng từ giai đoạn trước
COPY --from=build /usr/src/app/target/comicapi.jar /usr/local/lib/comicapi.jar

# Mở cổng 8080
EXPOSE 8080

# Đặt lệnh java -jar làm điểm vào để chạy ứng dụng
CMD ["java", "-jar", "/usr/local/lib/comicapi.jar"]
