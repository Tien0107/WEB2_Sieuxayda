@echo off
echo Importing sample data to MongoDB...

echo.
echo [1/3] Importing users...
mongoimport --db homework2 --collection users --file users.json --jsonArray

echo.
echo [2/3] Importing companies...
mongoimport --db homework2 --collection companies --file companies.json --jsonArray

echo.
echo [3/3] Importing staffs...
mongoimport --db homework2 --collection staffs --file staffs.json --jsonArray

echo.
echo Import completed!
echo.
echo Login credentials:
echo - Admin: username=admin, password=123456
echo - User: username=user, password=123456
echo.
pause
