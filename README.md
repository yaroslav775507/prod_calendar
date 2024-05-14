<h1>Производственный календарь на май</h1>
<h2>Задание:</h2>
1 уровень:
Необходимо разработать метод, который будет получать на вход дату, а на выходе возвращать true или false
в соответствии с производственным календарем на май 2024 года
(https://www.consultant.ru/law/ref/calendar/proizvodstvennye/2024/).
true - если дата является выходным (не рабочим днем)
false - если дата является рабочим днем.

2 уровень:
Добавить еще один метод для проверки даты и времени с учетом часового пояса на принадлежность к
рабочему времени в соответствии
с установленным графиком работы и производственным календарем.
Дополнительно необходимо осуществлять проверку на время и возвращать:
true - если дата и время НЕ являются рабочими в соответствии с производственным календарем и
установленным графиком работы
false - если дата и время являются рабочими в соответствии с производственным календарем и
установленным графиком работы
Установленный график работы:
40-часовая 5-дневная рабочая неделя с двумя выходными днями (суббота и воскресенье).
Рабочее время с 9.00 до 18.00 по Московскому времени, с 45-ти минутным перерывом на обед
(может быть использован в любое время в течении рабочего дня, на результат работы метода не влияет).


<h2>Метод который проверяет входит ли выбранный день в массив</h2>
<code> 
public static boolean sendDate(LocalDate variable) {
        int[] weekendDays = {1, 4, 5, 9, 10, 11, 12, 18, 19, 25, 26};
        int nowDay = variable.getDayOfMonth();
        for (int weekendDay : weekendDays) {
            if (weekendDay == nowDay) {
                return true;
            }
        }
        return false;
    }
</code>



<h2>Метод который проверяет еще и время</h2>



<code> 
 public static boolean checkDateTime(LocalDate date, LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        ZonedDateTime moscowDateTime = dateTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalTime startOfWorkDay = LocalTime.of(9, 0);
        LocalTime endOfWorkDay = LocalTime.of(18, 0);
        DayOfWeek dayOfWeek = moscowDateTime.getDayOfWeek();
        boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
        LocalTime moscowTime = moscowDateTime.toLocalTime();
        boolean isWorkingTime = !moscowTime.isBefore(startOfWorkDay) && !moscowTime.isAfter(endOfWorkDay);
        return isWeekend || !isWorkingTime;
    }
</code>

Вывод результата:
<img width="1409" alt="Снимок экрана 2024-05-14 в 17 43 17" src="https://github.com/yaroslav775507/prod_calendar/assets/103926398/bdb53806-2c6e-4f06-a542-264334cd5ea7">
---------------------
<h2>Дополнение:</h2>

По запросу http://localhost:8080/date-input 
открывается web страница в которой необходимо заполнить данные

<img width="1439" alt="Снимок экрана 2024-05-14 в 17 45 37" src="https://github.com/yaroslav775507/prod_calendar/assets/103926398/e507f9e7-cbab-4297-9c95-359ca4a199b7">

В зависимости от выбора даты откроется старничка которая покажет выходной(после-рабочее время) либо рабочий день.
<br>

<img width="1440" alt="Снимок экрана 2024-05-14 в 17 49 14" src="https://github.com/yaroslav775507/prod_calendar/assets/103926398/e8d94fec-af64-411f-b244-3359051c3d9e">
<img width="1440" alt="Снимок экрана 2024-05-14 в 17 49 30" src="https://github.com/yaroslav775507/prod_calendar/assets/103926398/546dd304-b765-485d-a94c-028aa2ebc56d">






