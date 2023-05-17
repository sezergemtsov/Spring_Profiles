### Spring - working with profiles

Данное приложение предназначено для отработки возможностей которые предоставляет Spring в работе с переменными окружения и настройками окружения в одном приложении для разных задач.  


#### Переменные среды 

Spring предолставляет два механизма работы с переменными среды в коде:  
- через аннотацию @Value("${property}")
- через POJO и аннотацию @ConfigurationProperties("some.properties")  

Перывый способ хорош в том случае когда переменных среды которые мы хотим использовать в коде не много.  
Однако, в случае группировки большого количества информации, которую мы хотим притащить извне, гораздо лаконичнее будет смотреться второй вариант.  
Оба варианта применены в коде:  
- пример для первого: [@Value("${property}")]([https://github.com](https://github.com/sezergemtsov/Spring_Profiles/blob/master/src/main/java/sezergemtsov/profiles/controllers/Controller.java#L21))
- пример для второго: [@ConfigurationProperties]([https://github.com](https://github.com/sezergemtsov/Spring_Profiles/blob/master/src/main/java/sezergemtsov/profiles/configs/H2Config.java))  

#### Запуск приложения с разными конфигурациями

Достаточно удобно иметь разные конфигурации запуска одного приложения для тестов отладки или каких то других целей.
Spring позволяет сделать это посредством подготовки конфигурационных файлов application.properties  
В данном случае выбор расширения между .properties и .yaml остается на приверженность предпочтениям, и реализовать механизм можно в обоих случаях.
В данном проекте подготовлены 2 файла с небольшими различиями:
- название базы данных testdb и proddb
- значение переменной окружения которая подтянется на страницу входа при запуске  

Существует достаточно много статей как устроен этот механизм, но с моей точки зрения практически везде опускается базовые вещи, 
которые кажутся сами собой разумеющимися для уже освоивших инструмент. Если мы хотим что-то выучить нужно помнить главное правило: глупых вопросов не бывает.

В случае с конфигурацией application.properties: ранее существовал механиз обозначения группы настроек,
который прописывался внутри файла примерно слудующим образом spring.profiles.active=production  
Сейчас при создании нового пректа эта настройка необязательна, так как теперь механизм реализован в связке с названием properties файла.  
Например, название файла application-production.properties будет полностью анологично настройке описанной выше.

Какой файл учавствует в запуске и в каком случае?  
По умолчанию подтянется файл без дополнительных конструкций. Чтобы изменить файл настроек нужно добавлять специальные команды при запуске.
Они будут различаться для разных сборщиков. В данном проекте использован Gradle, поэтому запуск с альтернативными настройками добавлен в отдельную задачу.  
Ее можно найти в блоке gradle -> other -> test_run


Мы уже изменили переменные среды в разных настройках, у базы данных можно изменить достаточно много - изменить драйвер, подключиться к postgres вместо H2, уже звучит достаточно внушительно,
но что еще можно сделать с этим инструментом?

#### Конфигурация бинов

Конфигурация среды позволяет также определять какие сущности будет создавать наше приложение.
В Spring мы можем использовать аннотацию @Profile("profile") над компонентом. В данном случае компонент будет инициализирован только в том случае, 
если выбран профиль который указан в аннотации.  
Для примера в данном приложении конфигурация [Security]([https://github.com](https://github.com/sezergemtsov/Spring_Profiles/blob/master/src/main/java/sezergemtsov/profiles/configs/SecurityConfig.java#L15-L27)) для test_run отключает аутентификацию, 
но в случае обычной загрузки применяется стандартная реализация аутентификации по логину user и паролю сгенерированну приложением в момент запуска.

Здесь же стоит упомянуть еще об одном механизме конфигурации бинов которые предоставляет Spring.
Это аннотация: @ConditionalOnProperty(name = "my.property", havingValue = "value") - [пример в коде.](https://github.com/sezergemtsov/Spring_Profiles/blob/master/src/main/java/sezergemtsov/profiles/configs/SecurityConfig.java#L14)
В отличие от профилей она работает более точечно, но для больших групп настроек потльзоваться ей будет не так удобно.
