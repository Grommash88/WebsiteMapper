# WebsiteMapper

eng:

The program creates a map of links on the site specified by the user.

After launching the program, you need to enter the name of the site in the format "yandex.ru" into the console,
valid domains:

        -.ru
        -.com
        -.org
        -.ua
After entering, the string is converted to the format of a link to the home page of the specified site,
the recursive algorithm is launched, implemented in the com.grommash88.app.mapper.SiteMapper class,
in the com.grommash88.app.mapper.NodeImpl class that implements the interface
com.grommash88.app.mapper.Node, implemented getting links placed on the page to
which is connected.

Connection settings are described in the com.grommash88.app.util.properties.Props class.

String validation is implemented in the com.grommash88.app.util.Validator class.

Logging of messages and exceptions is implemented in the com.grommash88.app.util.logger.AppLogger class,
log messages are described in the com.grommash88.app.util.logger.Msgs class.
Logging settings are described in the srs / main / resources / log4j2.xml file

The com.grommash88.app.exceptions.PageNotFoundException class is implemented to handle 404s from the site.

Writing to a file saved in the site_maps folder is implemented in the com.grommash88.app.Main class.

Mapping time depends on the size of the site, a small site is mapped for about 20 minutes.

ru:

Программа составляет карту ссылок на указанном пользователем сайте.

После запуска программы в консоль требуется ввести название сайта в формате "yandex.ru",
валидные домены:

        -.ru
        -.com
        -.org
        -.ua
После ввода, строка приводится к формату ссылки на главную страницу указанного сайта,  
запускается рекурсивный алгоритм, реализованный в классе com.grommash88.app.mapper.SiteMapper,
в классе com.grommash88.app.mapper.NodeImpl, имплементирующем интерфейс
com.grommash88.app.mapper.Node, реализовано получение ссылок размещенных на странице к
которой происходит подключение.

Настройки подключения описаны в классе com.grommash88.app.util.properties.Props.

Валидация строк реализована в классе com.grommash88.app.util.Validator.

Логирование сообщений и исключений реализовано в классе com.grommash88.app.util.logger.AppLogger,
сообщения логирования описаны в классе com.grommash88.app.util.logger.Msgs.
Настройки логирования описаны в файле srs/main/resources/log4j2.xml

Класс com.grommash88.app.exceptions.PageNotFoundException реализован для обработки 404 от сайта.

Запись в файл, сохраняемый в папке site_maps реализованна в классе com.grommash88.app.Main.

Время маппинга зависит от размера сайта, небольшой сайт маппится примерно 20 минут.
