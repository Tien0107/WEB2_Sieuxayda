# MongoDB Import Guide

## Cách import dữ liệu mẫu vào MongoDB

### 1. Khởi động MongoDB
```bash
# Windows
net start MongoDB

# Linux/Mac
sudo systemctl start mongod
```

### 2. Import dữ liệu theo thứ tự

**Bước 1: Import Users**
```bash
mongoimport --db homework2 --collection users --file users.json --jsonArray
```

**Bước 2: Import Companies**
```bash
mongoimport --db homework2 --collection companies --file companies.json --jsonArray
```

**Bước 3: Import Staffs**
```bash
mongoimport --db homework2 --collection staffs --file staffs.json --jsonArray
```

### 3. Kiểm tra dữ liệu đã import

```bash
# Kết nối MongoDB
mongo

# Chọn database
use homework2

# Kiểm tra số lượng documents
db.users.count()
db.companies.count()
db.staffs.count()

# Xem dữ liệu
db.users.find().pretty()
db.companies.find().pretty()
db.staffs.find().pretty()
```

### 4. Thông tin đăng nhập mẫu

- **Admin:** username: `admin`, password: `123456`
- **User:** username: `user`, password: `123456`

### 5. Lưu ý

- Password đã được mã hóa bằng BCrypt
- Các `_id` được định nghĩa sẵn để đảm bảo tham chiếu giữa collections
- `companyId` trong staffs tham chiếu đến `_id` trong companies
