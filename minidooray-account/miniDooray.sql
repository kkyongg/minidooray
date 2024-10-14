DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
	`id`	BIGINT	NOT NULL,
	`user_id`	VARCHAR(50)	NOT NULL,
	`email`	VARCHAR(50)	NOT NULL,
	`password`	VARCHAR(200)	NOT NULL,
	`last_login_at`	DATETIME	NULL,
	`status`	VARCHAR	NOT NULL
);

DROP TABLE IF EXISTS `Project`;

CREATE TABLE `Project` (
	`id`	BIGINT	NOT NULL,
	`name`	VARCHAR(100)	NOT NULL,
	`status`	VARCHAR(10)	NOT NULL,
	`created_at`	DATETIME	NOT NULL,
	`admin_id`	BIGINT	NOT NULL
);

DROP TABLE IF EXISTS `Task`;

CREATE TABLE `Task` (
	`id`	BIGINT	NOT NULL,
	`name`	VARHAR(100)	NOT NULL,
	`content`	TEXT	NOT NULL,
	`created_at`	DATETIME	NOT NULL,
	`user_id`	BIGINT	NOT NULL,
	`project_id`	BIGINT	NOT NULL
);

DROP TABLE IF EXISTS `Tag`;

CREATE TABLE `Tag` (
	`id`	BIGINT	NOT NULL,
	`name`	VARCHAR(30)	NOT NULL,
	`project_id`	BIGINT	NOT NULL
);

DROP TABLE IF EXISTS `TaskTag`;

CREATE TABLE `TaskTag` (
	`id`	BIGINT	NOT NULL,
	`task_id`	BIGINT	NOT NULL,
	`tag_id`	BIGINT	NOT NULL
);

DROP TABLE IF EXISTS `MileStone`;

CREATE TABLE `MileStone` (
	`id`	BIGINT	NOT NULL,
	`status`	VARCHAR(30)	NOT NULL,
	`project_id`	BIGINT	NOT NULL
);

DROP TABLE IF EXISTS `TaskMileStone`;

CREATE TABLE `TaskMileStone` (
	`id`	BIGINT	NOT NULL,
	`task_id`	BIGINT	NOT NULL,
	`milestone_id`	BIGINT	NOT NULL
);

DROP TABLE IF EXISTS `Comment`;

CREATE TABLE `Comment` (
	`id`	BIGINT	NOT NULL,
	`content`	TEXT	NOT NULL,
	`created_at`	DATETIME	NOT NULL,
	`task_id`	BIGINT	NOT NULL,
	`projectMember_id`	BIGINT	NOT NULL
);

DROP TABLE IF EXISTS `ProjectMember`;

CREATE TABLE `ProjectMember` (
	`id`	BIGINT	NOT NULL,
	`project_id`	BIGINT	NOT NULL,
	`member_id`	BIGINT	NOT NULL
);

ALTER TABLE `User` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`id`
);

ALTER TABLE `Project` ADD CONSTRAINT `PK_PROJECT` PRIMARY KEY (
	`id`
);

ALTER TABLE `Task` ADD CONSTRAINT `PK_TASK` PRIMARY KEY (
	`id`
);

ALTER TABLE `Tag` ADD CONSTRAINT `PK_TAG` PRIMARY KEY (
	`id`
);

ALTER TABLE `TaskTag` ADD CONSTRAINT `PK_TASKTAG` PRIMARY KEY (
	`id`
);

ALTER TABLE `MileStone` ADD CONSTRAINT `PK_MILESTONE` PRIMARY KEY (
	`id`
);

ALTER TABLE `TaskMileStone` ADD CONSTRAINT `PK_TASKMILESTONE` PRIMARY KEY (
	`id`
);

ALTER TABLE `Comment` ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (
	`id`
);

ALTER TABLE `ProjectMember` ADD CONSTRAINT `PK_PROJECTMEMBER` PRIMARY KEY (
	`id`
);

ALTER TABLE `Project` ADD CONSTRAINT `FK_User_TO_Project_1` FOREIGN KEY (
	`admin_id`
)
REFERENCES `User` (
	`id`
);

ALTER TABLE `Task` ADD CONSTRAINT `FK_User_TO_Task_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User` (
	`id`
);

ALTER TABLE `Task` ADD CONSTRAINT `FK_Project_TO_Task_1` FOREIGN KEY (
	`project_id`
)
REFERENCES `Project` (
	`id`
);

ALTER TABLE `Tag` ADD CONSTRAINT `FK_Project_TO_Tag_1` FOREIGN KEY (
	`project_id`
)
REFERENCES `Project` (
	`id`
);

ALTER TABLE `TaskTag` ADD CONSTRAINT `FK_Task_TO_TaskTag_1` FOREIGN KEY (
	`task_id`
)
REFERENCES `Task` (
	`id`
);

ALTER TABLE `TaskTag` ADD CONSTRAINT `FK_Tag_TO_TaskTag_1` FOREIGN KEY (
	`tag_id`
)
REFERENCES `Tag` (
	`id`
);

ALTER TABLE `MileStone` ADD CONSTRAINT `FK_Project_TO_MileStone_1` FOREIGN KEY (
	`project_id`
)
REFERENCES `Project` (
	`id`
);

ALTER TABLE `TaskMileStone` ADD CONSTRAINT `FK_Task_TO_TaskMileStone_1` FOREIGN KEY (
	`task_id`
)
REFERENCES `Task` (
	`id`
);

ALTER TABLE `TaskMileStone` ADD CONSTRAINT `FK_MileStone_TO_TaskMileStone_1` FOREIGN KEY (
	`milestone_id`
)
REFERENCES `MileStone` (
	`id`
);

ALTER TABLE `Comment` ADD CONSTRAINT `FK_Task_TO_Comment_1` FOREIGN KEY (
	`task_id`
)
REFERENCES `Task` (
	`id`
);

ALTER TABLE `Comment` ADD CONSTRAINT `FK_ProjectMember_TO_Comment_1` FOREIGN KEY (
	`projectMember_id`
)
REFERENCES `ProjectMember` (
	`id`
);

ALTER TABLE `ProjectMember` ADD CONSTRAINT `FK_Project_TO_ProjectMember_1` FOREIGN KEY (
	`project_id`
)
REFERENCES `Project` (
	`id`
);

ALTER TABLE `ProjectMember` ADD CONSTRAINT `FK_User_TO_ProjectMember_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `User` (
	`id`
);

