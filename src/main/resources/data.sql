-- quiz data --
INSERT INTO quiz(id, name, description) VALUES (1, 'Basic GK Quiz', 'This quiz will help to polish your unawareness about General Knowledge.');
INSERT INTO quiz(id, name, description) VALUES (2, 'Sports Quiz', 'The Sports Quiz consists of different questions from countries and national games and also Olympic games.');
INSERT INTO quiz(id, name, description) VALUES (3, 'Indian Geography', 'Indian Geography Online Test contains various questions that are useful for every candidate to face multiple competitive exams.');

-- questions data --
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (1, 'Which crop is sown on the largest area in India?', 'Rice,Wheat,Sugarcane,Maize', 1, 1, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (2, 'Corey Anderson who has hit the fastest ODI century in 36 balls is from?', 'England,Australia,WI,NZ', 4, 1, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (3, 'The world smallest country is?', 'Italy,Canada,Vatican City,Russia', 3, 1, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (4, 'Novak Djokovic is a famous player associated with the game of?', 'Hockey,Football,Chess,Lawn Tennis', 4, 1, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (5, 'Which country below is not one of the members of the UN security council?', 'Germany,China,Russia,France', 1, 1, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (6, 'Sultan Azlan Shah Cup is related to which among the following Sports?', 'Badminton,Hockey,Table Tennis,Golf', 2, 2, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (7, 'Which is the national sport of Canada?', 'Ice Hockey,Cricket,Volleyball,Football', 1, 2, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (8, '2010 Commonwealth Games held in?', 'Russia,UK,Australia,India', 4, 2, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (9, 'The term "Slam" is associated with?', 'Cricket,Tennis,Boxing,Football', 2, 2, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (10, 'World cup of hockey is held after the gap of __________ years?', '1,2,3,4', 4, 2, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (11, 'India is located on which part of Indo-Australian Plate?', 'Southern,Western,Northern,Eastern', 3, 3, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (12, 'Which one of the major source of irrigation in India?', 'Canals,Wells,Other Sources,Tanks', 2, 3, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (13, 'The Mangroove forests of the Gangetic delta (West Bengal) are called?', 'Sholas,Sunderbans,Kalibans,Evergreen Forests', 2, 3, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (14, 'Corbett National Park is in?', 'Bihar,Assam,Uttarakhand,Madhya Pradesh', 3, 3, 1);
INSERT INTO question(id, name , options, correct_option, quiz_id, points) VALUES (15, 'Which city is known as the "Queen of the Arabian Sea"?', 'Vishakhapatnam,Kochi,Kollam,Panaji', 2, 3, 1);

-- user data --
INSERT INTO users(id, email, auth_token) VALUES (1, 'abc@gmail.com', '19c4ff12-e027-4320-b844-2cda768714e8');