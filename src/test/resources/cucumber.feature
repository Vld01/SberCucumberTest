#language: ru

@all
Функционал: Ипотека

  @negative
  @sberbank
  Сценарий: Работа с Cucumber на примере Ипотеки сбербанка
    * Загружена стартовая страница
    * Переход в меню Ипотека и В подменю выбираем Ипотека на готовое жильё
    * Заполняем форму поле/значение
      |Стоимость недвижимости                           |5180000|
      |Первоначальный взнос                             |3058000|
      |Срок кредита                                     |30     |

    * Устанавливаем переключатели
      |Скидка 0,3% при покупке квартиры на ДомКлик      |false  |
      |Страхование жизни                                |false  |
      |Молодая семья                                    |true   |
      |Электронная регистрация сделки                   |false  |

    * Сравниваем значения
      |Сумма кредита         |2 122 000|
      |Ежемесячный платеж    |16 922  |
      |Необходимый доход     |21 784  |
      |Процентная ставка     |11%    |
