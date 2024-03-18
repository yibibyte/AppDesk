@echo off
set JAVA_FX_URL=https://download2.gluonhq.com/javafx/21.0.2/javafx-sdk-21.0.2.zip
set INSTALL_DIR=C:\JavaFX

:: Создание директории для установки JavaFX
mkdir %INSTALL_DIR%

:: Скачивание JavaFX
curl -o javafx.zip %JAVA_FX_URL%

:: Распаковка JavaFX
tar -xf javafx.zip -C %INSTALL_DIR%

:: Удаление временного архива
del javafx.zip

echo JavaFX 21 успешно установлен в %INSTALL_DIR%
