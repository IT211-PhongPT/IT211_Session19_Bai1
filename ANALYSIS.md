# Phân Tích Logic - Bài Tập 1: Token Hết Hạn Sớm

## 1. Nguyên nhân gốc rễ của vấn đề
Trong mã nguồn ban đầu của lớp `TokenService`, thời gian hết hạn của Access Token được thiết lập thông qua hằng số:
```java
private final long ACCESS_TOKEN_EXPIRATION_MS = TimeUnit.SECONDS.toMillis(30);
```
Dòng mã này chuyển đổi 30 giây thành mili giây (tương đương 30,000 ms). Điều này trực tiếp vi phạm yêu cầu nghiệp vụ hệ thống là Access Token phải có thời hạn sử dụng 15 phút. Vì thời hạn sống quá ngắn (chỉ 30 giây), phiên làm việc của người dùng liên tục bị vô hiệu hóa, bắt buộc họ phải đăng nhập lại mỗi nửa phút, làm gián đoạn trải nghiệm người dùng nghiêm trọng.

## 2. Giải pháp khắc phục
* Thay thế cấu hình thời gian sống thành 15 phút bằng cách sử dụng `TimeUnit.MINUTES.toMillis(15)`.
* Tăng cường tính bảo mật của mã khóa ký bằng cách nâng cấp `SECRET_KEY` lên chuỗi có độ dài tối thiểu 256-bit (32 ký tự/bytes) nhằm tránh lỗi `WeakKeyException` từ các phiên bản thư viện JJWT mới.
* Sử dụng API không bị deprecated của thư viện JJWT (`parserBuilder()` và `Keys.hmacShaKeyFor`) để thực hiện các thao tác ký số và giải mã mã thông báo.
