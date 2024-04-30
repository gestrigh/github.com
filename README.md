# Проект-презентация по автоматизации для сайта "Lamoda.ru"

<a href="https://www.lamoda.ru/"> <img alt="Picture of site" src="/media/Lamoda_logo.svg.png">

## :clipboard: Содержание:

- <a href="#tools"> Стек используемых технологий</a>
- <a href="#checking"> Реализованные проверки</a>
- <a href="#console"> Запуск автотестов</a>
- <a href="#jenkins"> Сборка в Jenkins</a>
- <a href="#allureReport"> Пример Allure-отчета</a>
- <a href="#allureTestOps"> Интеграция с Allure TestOps</a>
- <a href="#jira"> Интеграция с Jira</a>
- <a href="#tg"> Уведомления в Telegram с использованием бота</a>
- <a href="#video"> Видео примера запуска тестов в Selenoid</a>

<a id="tools"></a>
## :wrench: Стек используемых технологий

<p align="center">
<a href="https://www.w3schools.com/java/"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" alt="java" width="40" height="40"/> </a>
<a href="https://selenide.org/"> <img src="https://ru.selenide.org/images/selenide-logo-big.png" alt="selenide" width="80" height="40"/> </a>
<a href="https://junit.org/junit5/"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/junit/junit-original.svg" alt="junit" width="40" height="40"/> </a>
<a href="https://gradle.org/"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/gradle/gradle-original.svg" alt="gradle" width="40" height="40"/> </a>
<a href="https://aerokube.com/selenoid-ui/latest/"> <img src="https://aerokube.com/img/aerokube_logo.svg" alt="selenoid" width="40" height="40"/> </a>
<a href="https://docs.qameta.io/allure-testops/"> <img src="https://plugins.jetbrains.com/files/12513/451639/icon/pluginIcon.svg" alt="allure testOps" width="35" height="35"/> </a>
<a href="https://allurereport.org/"> <img src="https://avatars.githubusercontent.com/u/5879127?s=200&v=4" alt="allure report" width="40" height="40"/> </a>
<a href="https://www.atlassian.com/software/jira"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jira/jira-original-wordmark.svg" alt="jira" width="40" height="40"/> </a>
<a href="https://www.jenkins.io/"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jenkins/jenkins-original.svg" alt="jenkins" width="40" height="40"/> </a>
<a href="https://web.telegram.org/"> <img src="https://upload.wikimedia.org/wikipedia/commons/8/83/Telegram_2019_Logo.svg" alt="telegram" width="40" height="40"/> </a>
</p>

Тесты в данном проекте написаны на языке ```Java``` с использованием фреймворка для тестирования ```Selenide```, сборщик - ```Gradle```.

```JUnit 5``` задействован в качестве фреймворка модульного тестирования.
При прогоне тестов для запуска браузеров используется ```Selenoid```.

Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота. Так же реализована интеграция с <code>Allure TestOps</code> и <code>Jira</code>.

Содержание Allure-отчета:
* Шаги теста;
* Скриншот страницы на последнем шаге;
* Page Source;
* Логи браузерной консоли;
* Видео выполнения автотеста.

<a id="checking"></a>
## :male_detective: Реализованные проверки

Автоматизированные тесты:
1. Добавление товара в корзину (tag - cart)
- Добавление товара в корзину
- Удаление товара из корзины
2. Раздел Идеи (tag - ideas)
- Проверка отображения карточек блоггеров
- Проверка отображения образов
3. Проверка отображения аттрибутов скидки у скидочного товара (tag - saleCategory)
4. Поиск товара с помощью поисковой строки (tag - search)
- [Параметризованный тест] Поиск товара с помощью поисковой строки
- Поиск товара с применением фильтров

Ручные тесты:
1. Авторизация в личный кабинет

<a id="console"></a>
## :arrow_forward: Запуск автотестов

### Запуск тестов из терминала

В проекте присутствуют отдельные теги для каждого тест-сьюта (описаны выше в реализованных проверках), с помощью которых,
можно запускать не только все тесты единоразово, но и отдельно только нужные. Применимо и при локальном и удаленном запуске.
Локальный запуск тестов по сьютам "cart,ideas" без доп. параметров:
```
gradle clean withTags -Dtags=cart,ideas
```
Локальный запуск всех тестов без доп. параметров:
```
gradle clean withTags
```
Удаленный запуск всех тестов с параметрами:
```
gradle clean withTags -Denv=remote -Dbrowser=chrome -DbrowserVersion=122.0 -DbrowserSize=1920x1080 -DbrowserRemoteUrl=*Remote selenoid or moon hub* -DbaseUrl=https://lamoda.ru
```

<a id="jenkins"></a>
## <img width="5%" style="vertical-align:middle" title="Jenkins" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jenkins/jenkins-original.svg"> </a> Сборка в <a target="_blank" href="https://jenkins.autotests.cloud/view/C24/job/C24_TimofeevRoman-Lamoda-UI-diploma/"> Jenkins </a>

Для запуска сборки необходимо перейти в раздел ```Build with Parameters```, выбрать необходимые параметры и нажать кнопку ```Build```.
#### :hammer_and_wrench: Параметры сборки в Jenkins:
- BROWSER (браузер: chrome/firefox, по умолчанию chrome)
- BROWSER_VERSION (версия браузера, по умолчанию 121.0)
- BROWSER_SIZE (размер окна браузера, по умолчанию 1920x1080)
- BROWSER_REMOTE_URL (адрес удаленного сервера для запуска)
- BASE_URL (адрес базового URL продукта Lamoda, по умолчанию https://lamoda.ru)
- TAGS (теги конкретных автотестов для запуска, по умолчанию smoke)
<p align="center">
<img title="Jenkins Build" src="media/jenkinsMain.png">
</p>

После выполнения сборки, в блоке ```Build History``` напротив номера сборки появятся значки ```Allure Report``` и ```Allure TestOps```, при клике на которые откроется страница с сформированным html-отчетом и тестовой документацией соответственно.

<a id="allureReport"></a>
## <img width="5%" style="vertical-align:middle" title="Allure Report" src="https://avatars.githubusercontent.com/u/5879127?s=200&v=4"> </a> Пример <a target="_blank" href="https://jenkins.autotests.cloud/view/C24/job/C24_TimofeevRoman-Lamoda-UI-diploma/allure/"> Allure-отчета </a>
### Overview

<p align="center">
<img title="Allure Overview" src="media/allureReport.png">
</p>

<a id="allureTestOps"></a>
## <img width="3%" style="vertical-align:middle" title="Allure TestOps" src="https://plugins.jetbrains.com/files/12513/451639/icon/pluginIcon.svg"> </a> Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/4205/dashboards"> Allure TestOps </a>

На *Dashboard* в ```Allure TestOps``` видна статистика количества тестов: сколько из них добавлены и проходятся вручную, сколько автоматизированы. Новые тесты, а так же результаты прогона приходят по интеграции при каждом запуске сборки.

<p align="center">
<img title="Allure TestOps DashBoard" src="media/allureTestOps.png">
</p>

### Результат выполнения автотеста в прогоне

<p align="center">
<img title="Test Results in Alure TestOps" src="media/allureTestOpsDetail.png">
</p>

<a id="jira"></a>
## <img width="4%" style="vertical-align:middle" title="Jira" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jira/jira-original-wordmark.svg"> </a> Интеграция с <a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-1204"> Jira </a>

Реализована интеграция ```Allure TestOps``` с ```Jira```, в тикете отображается, какие тест-кейсы были написаны в рамках задачи и результат их прогона.

<p align="center">
<img title="Jira Task" src="media/Jira.png">
</p>

И наоборот, у тест кейсов отображается ссылка на тикет в Jira, во вкладке ```Test cases```, в поле ```Issue links```.

<p align="center">
<img title="Allure TestOps test case" src="media/allureTestOpsTestCase.png">
</p>

<a id="tg"></a>
## <img width="4%" style="vertical-align:middle" title="Telegram" src="https://upload.wikimedia.org/wikipedia/commons/8/83/Telegram_2019_Logo.svg"> Уведомления в Telegram с использованием бота

После завершения сборки специальный бот, созданный в ```Telegram```, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне тестов и информацией о нём. Если тесты не прошли успешно, а получили статус "Сломанный" или "Упавший" эта информация отразится в диаграмме.

<p align="center">
<img width="50%" title="Telegram Notifications" src="media/tg.png">
</p>

<a id="video"></a>
## <img width="4%" style="vertical-align:middle" title="Selenoid" src="https://aerokube.com/img/aerokube_logo.svg"> Видео примера запуска тестов в Selenoid

В отчетах Allure для каждого теста прикреплен не только скриншот, но и видео прохождения теста.
<p align="center">
  <img title="Selenoid Video" src="media/video.gif">
</p>