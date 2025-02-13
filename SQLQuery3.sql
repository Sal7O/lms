USE [master];
DROP DATABASE IF EXISTS [lms];
CREATE DATABASE [lms];
USE [lms];

-- create user's table
CREATE TABLE [user] (
	[id] INT IDENTITY,
	[first_name] VARCHAR(64) NOT NULL,
	[last_name] VARCHAR(64) NOT NULL,
	[user_name] VARCHAR(64) NOT NULL UNIQUE,
	[email] VARCHAR(64) NOT NULL UNIQUE,
	[password] VARCHAR(64) NOT NULL,
	[phone_number]	VARCHAR(11) NOT NULL,
	[address] TEXT,
	[role] VARCHAR(20) NOT NULL CHECK ([role] IN ('ADMIN', 'STUDENT', 'INSTRUCTOR')) DEFAULT 'STUDENT',
	[photo] VARBINARY(MAX),

	PRIMARY KEY([id])
);

-- create course's table
CREATE TABLE [course] (
	[id] INT IDENTITY,
	[name] VARCHAR(20) NOT NULL,
	[description] TEXT,
	[duration] INT NOT NULL,

	PRIMARY KEY([id]),
);

-- student x enrolls in course y
CREATE TABLE [enrolls] (
	[student_id] INT,
	[course_id] INT,

	PRIMARY KEY ([student_id], [course_id]),

	FOREIGN KEY([student_id]) REFERENCES [user]([id]),
	FOREIGN KEY([course_id]) REFERENCES [course]([id])
);

-- instructor x instructs in course y
CREATE TABLE [instructs] (
	[instructor_id] INT,
	[course_id] INT,

	PRIMARY KEY ([instructor_id], [course_id]),

	FOREIGN KEY([instructor_id]) REFERENCES [user]([id]),
	FOREIGN KEY([course_id]) REFERENCES [course]([id])
);

-- create notifications' table
CREATE TABLE [notification] (
	[id] INT IDENTITY,
	[description] TEXT NOT NULL,
	[is_read] BIT NOT NULL DEFAULT 0,
	[user_id] INT NOT NULL,

	PRIMARY KEY([id]),

	FOREIGN KEY ([user_id]) REFERENCES [user]([id]) ON DELETE CASCADE
);


-- create lesson's table
CREATE TABLE [lesson] (
	[id] INT IDENTITY,
	[OTP] VARCHAR(64) NOT NULL,
	[date] DATE NOT NULL,
	[course_id] INT NOT NULL,
	[instructor_id] INT NOT NULL,

	PRIMARY KEY([id]),

	FOREIGN KEY([course_id]) REFERENCES [course]([id]),
	FOREIGN KEY([instructor_id]) REFERENCES [user]([id])
);

-- student x attends lesson y
CREATE TABLE [attend] (
	[student_id] INT NOT NULL,
	[lesson_id] INT NOT NULL,

	PRIMARY KEY([student_id], [lesson_id]),

	FOREIGN KEY([student_id]) REFERENCES [user]([id]) ON DELETE CASCADE,
	FOREIGN KEY([lesson_id]) REFERENCES [lesson]([id]) ON DELETE CASCADE
);

-- create table of media
CREATE TABLE [media] (
	[id] INT IDENTITY,
	[file] VARBINARY(MAX) NOT NULL,
	[type] VARCHAR(20) NOT NULL,
	[course_id] INT NOT NULL,
	[instructor_id] INT NOT NULL,

	PRIMARY KEY([id]),

	FOREIGN KEY([course_id]) REFERENCES [course]([id]) ON DELETE CASCADE,
	FOREIGN KEY([instructor_id]) REFERENCES [user]([id]) ON DELETE CASCADE
);

-- create table assignment
CREATE TABLE [assignment] (
	[id] INT IDENTITY,
	[description] TEXT,
	[file] VARBINARY(MAX) NOT NULL,
	[course_id] INT NOT NULL,
	[instructor_id] INT NOT NULL,
	[max_score] INT NOT NULL,

	PRIMARY KEY([id]),

	FOREIGN KEY([course_id]) REFERENCES [course]([id]) ON DELETE CASCADE,
	FOREIGN KEY([instructor_id]) REFERENCES [user]([id]) ON DELETE CASCADE 
);

-- create assignment_submission's table
CREATE TABLE [assignment_submission] (
	[id] INT IDENTITY,
	[file] VARBINARY(MAX) NOT NULL,
	[score] INT,
	[student_id] INT NOT NULL,
	[assignment_id]INT NOT NULL,
	[evaluator_id] INT,

	PRIMARY KEY([id]),

	FOREIGN KEY([student_id]) REFERENCES [user]([id]) ON DELETE CASCADE,
	FOREIGN KEY([evaluator_id]) REFERENCES [user]([id]),
	FOREIGN KEY([assignment_id]) REFERENCES [assignment]([id]),

);

-- create question_bank's table
CREATE TABLE [question_bank](
	[id] INT IDENTITY,
	[question] TEXT NOT NULL,
	[type] VARCHAR(20) NOT NULL CHECK(TYPE IN('MCQ', 'TRUE_FALSE')),
	[course_id] INT NOT NULL,

	PRIMARY KEY([id]),

	FOREIGN KEY([course_id]) REFERENCES [course]([id]) ON DELETE CASCADE
);

-- create mcq_choice table
CREATE TABLE [mcq_choice] (
	[id] INT IDENTITY,
	[value] TEXT NOT NULL,
	[is_answer] BIT NOT NULL DEFAULT 0,
	[question_id] INT NOT NULL,

	PRIMARY KEY([id]),

	FOREIGN KEY([question_id]) REFERENCES [question_bank]([id]) ON DELETE CASCADE
);

-- create true_false_ans
CREATE TABLE [true_false_ans] (
	[id] INT IDENTITY,
	[value] BIT NOT NULL DEFAULT 0,
	[question_id] INT NOT NULL,
	
	PRIMARY KEY([id]),
	FOREIGN KEY([question_id]) REFERENCES [question_bank]([id]) ON DELETE CASCADE
);

-- create table quiz
CREATE TABLE [quiz] (
	[id] INT IDENTITY,
	[course_id] INT NOT NULL,

	PRIMARY KEY([id]),

	FOREIGN KEY([course_id]) REFERENCES [course]([id])  ON DELETE CASCADE
);

-- quiz x has question y
CREATE TABLE [question_in_quiz] (
	[quiz_id] INT,
	[question_id] INT,

	PRIMARY KEY([quiz_id],[question_id]),

	FOREIGN KEY([quiz_id]) REFERENCES [quiz]([id]) ON DELETE CASCADE,
	FOREIGN KEY([question_id]) REFERENCES [question_bank]([id]),
);

-- create quiz submission's table
CREATE TABLE [quiz_submission] (
	[id] INT IDENTITY,
	[choice_id] INT,
	[question_id] INT NOT NULL,
	[true_false_id] INT,
	[student_id] INT NOT NULL,
	[quiz_id] INT NOT NULL,

	PRIMARY KEY([id]),

	FOREIGN KEY([student_id]) REFERENCES [user]([id]) ON DELETE CASCADE,
	FOREIGN KEY([quiz_id]) REFERENCES [quiz]([id]) ON DELETE CASCADE
);