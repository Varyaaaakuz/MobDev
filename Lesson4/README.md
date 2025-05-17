Для начала был создан новый проект Lesson4, а далее модуль mplayer. В данном модуле был создан экран музыкального плеера с использованием «binding» для горизонтальной и портретной ориентации.

![image](https://github.com/user-attachments/assets/b2456645-8d45-4ad7-86d5-79399129fa1f)
![image](https://github.com/user-attachments/assets/0b376964-3ff3-4efd-abf3-045e0d8c8da9)
![image](https://github.com/user-attachments/assets/78126368-5e60-4d91-b248-c685db5133dd)
![image](https://github.com/user-attachments/assets/d138ceec-e9e4-449a-afca-ed0c83a9e77f)
![image](https://github.com/user-attachments/assets/5ecb8a15-44d4-4cd0-9eb0-e34ba3ccb73c)
![image](https://github.com/user-attachments/assets/c21b1684-e064-48e4-a411-1590a1a3610c)
![image](https://github.com/user-attachments/assets/b1bc8377-eb6a-4971-a1eb-c50328756df7)
![image](https://github.com/user-attachments/assets/156a1b30-4dc7-469f-91af-73c49e83aa3d)
![image](https://github.com/user-attachments/assets/2a3fe29b-5aec-46e8-8cdc-a1e74350202e)
![image](https://github.com/user-attachments/assets/b8925d57-a035-4430-af96-dd11bcbe41da)
![image](https://github.com/user-attachments/assets/51be7c04-e615-4765-968f-d960147a9f8f)
![image](https://github.com/user-attachments/assets/2c9e2774-080e-46c0-bf7b-913170798752)
![image](https://github.com/user-attachments/assets/20786399-6f69-4cdc-a2fd-1416e491d738)
![image](https://github.com/user-attachments/assets/cf03c55b-a176-4e14-97a0-6635fba19093)
![image](https://github.com/user-attachments/assets/208629e3-4c20-435c-9de1-a9f7fe8adbf5)

В проекте «MireaProject» был создан отдельный фрагмент BackgroundTaskFragment для выполнения фоновой
задачи. Выполнение задачи было реализовано с помощью применения механизма «Worker». Был создан дополнительный макет, а также добавлен фрагмент в навигацию. 
Выполнена проверка подключения к сети (пока устройство не подключено к wifi фоновая задача находится в ожидании), а также была имитирована долгая операция, то есть задача завершается спустя 10 секунд. В logcat также выведены все статусы фоновой задачи.
Результаты выполнения работы:

![image](https://github.com/user-attachments/assets/527ec710-95e3-41c8-bfef-45eb0a91ec8a) ![image](https://github.com/user-attachments/assets/0e703693-c5f1-4dde-9332-1a5da8e14598)

![image](https://github.com/user-attachments/assets/3d28c8e0-5f67-4b15-b153-afd05e51d632) ![image](https://github.com/user-attachments/assets/16a99cf6-d7b7-4531-887b-b6dcbb3928a6)

![image](https://github.com/user-attachments/assets/1209a284-687e-4a69-8963-68c75409b450)

![image](https://github.com/user-attachments/assets/6645e27c-e70a-409b-bac3-c7d52b12b44a)

