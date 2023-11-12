-- hanol.BATCH_JOB_EXECUTION_SEQ definition

CREATE TABLE `test.BATCH_JOB_EXECUTION_SEQ` (
                                           `ID` bigint(20) NOT NULL,
                                           `UNIQUE_KEY` char(1) NOT NULL,
                                           UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.BATCH_JOB_INSTANCE definition

CREATE TABLE `test.BATCH_JOB_INSTANCE` (
                                      `JOB_INSTANCE_ID` bigint(20) NOT NULL,
                                      `VERSION` bigint(20) DEFAULT NULL,
                                      `JOB_NAME` varchar(100) NOT NULL,
                                      `JOB_KEY` varchar(32) NOT NULL,
                                      PRIMARY KEY (`JOB_INSTANCE_ID`),
                                      UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.BATCH_JOB_SEQ definition

CREATE TABLE `test.BATCH_JOB_SEQ` (
                                 `ID` bigint(20) NOT NULL,
                                 `UNIQUE_KEY` char(1) NOT NULL,
                                 UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.BATCH_STEP_EXECUTION_SEQ definition

CREATE TABLE `test.BATCH_STEP_EXECUTION_SEQ` (
                                            `ID` bigint(20) NOT NULL,
                                            `UNIQUE_KEY` char(1) NOT NULL,
                                            UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.`member` definition

CREATE TABLE `test.member` (
                          `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `created_date` datetime(6) NOT NULL,
                          `updated_date` datetime(6) DEFAULT NULL,
                          `birth` date DEFAULT NULL,
                          `email` varchar(255) NOT NULL,
                          `gender` int(11) DEFAULT NULL,
                          `last_login_date` datetime(6) DEFAULT NULL,
                          `name` varchar(255) NOT NULL,
                          `oauth_id` varchar(255) NOT NULL,
                          `oauth_provider` varchar(255) NOT NULL,
                          `role` int(11) NOT NULL,
                          PRIMARY KEY (`member_id`),
                          UNIQUE KEY `unique_oauth_member` (`oauth_provider`,`oauth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.`routine` definition

CREATE TABLE `test.routine` (
                           `routine_id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `is_default` tinyint(1) DEFAULT 0,
                           `is_value_1` tinyint(1) DEFAULT 0,
                           `is_value_2` tinyint(1) DEFAULT 0,
                           `is_value_3` tinyint(1) DEFAULT 0,
                           `is_value_4` tinyint(1) DEFAULT 0,
                           `is_value_5` tinyint(1) DEFAULT 0,
                           `is_value_6` tinyint(1) DEFAULT 0,
                           `routine_name` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`routine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.BATCH_JOB_EXECUTION definition

CREATE TABLE `test.BATCH_JOB_EXECUTION` (
                                       `JOB_EXECUTION_ID` bigint(20) NOT NULL,
                                       `VERSION` bigint(20) DEFAULT NULL,
                                       `JOB_INSTANCE_ID` bigint(20) NOT NULL,
                                       `CREATE_TIME` datetime(6) NOT NULL,
                                       `START_TIME` datetime(6) DEFAULT NULL,
                                       `END_TIME` datetime(6) DEFAULT NULL,
                                       `STATUS` varchar(10) DEFAULT NULL,
                                       `EXIT_CODE` varchar(2500) DEFAULT NULL,
                                       `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
                                       `LAST_UPDATED` datetime(6) DEFAULT NULL,
                                       `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
                                       PRIMARY KEY (`JOB_EXECUTION_ID`),
                                       KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
                                       CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `test.BATCH_JOB_INSTANCE` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.BATCH_JOB_EXECUTION_CONTEXT definition

CREATE TABLE `test.BATCH_JOB_EXECUTION_CONTEXT` (
                                               `JOB_EXECUTION_ID` bigint(20) NOT NULL,
                                               `SHORT_CONTEXT` varchar(2500) NOT NULL,
                                               `SERIALIZED_CONTEXT` text DEFAULT NULL,
                                               PRIMARY KEY (`JOB_EXECUTION_ID`),
                                               CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `test.BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.BATCH_JOB_EXECUTION_PARAMS definition

CREATE TABLE `test.BATCH_JOB_EXECUTION_PARAMS` (
                                              `JOB_EXECUTION_ID` bigint(20) NOT NULL,
                                              `TYPE_CD` varchar(6) NOT NULL,
                                              `KEY_NAME` varchar(100) NOT NULL,
                                              `STRING_VAL` varchar(250) DEFAULT NULL,
                                              `DATE_VAL` datetime(6) DEFAULT NULL,
                                              `LONG_VAL` bigint(20) DEFAULT NULL,
                                              `DOUBLE_VAL` double DEFAULT NULL,
                                              `IDENTIFYING` char(1) NOT NULL,
                                              KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
                                              CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `test.BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.BATCH_STEP_EXECUTION definition

CREATE TABLE `test.BATCH_STEP_EXECUTION` (
                                        `STEP_EXECUTION_ID` bigint(20) NOT NULL,
                                        `VERSION` bigint(20) NOT NULL,
                                        `STEP_NAME` varchar(100) NOT NULL,
                                        `JOB_EXECUTION_ID` bigint(20) NOT NULL,
                                        `START_TIME` datetime(6) NOT NULL,
                                        `END_TIME` datetime(6) DEFAULT NULL,
                                        `STATUS` varchar(10) DEFAULT NULL,
                                        `COMMIT_COUNT` bigint(20) DEFAULT NULL,
                                        `READ_COUNT` bigint(20) DEFAULT NULL,
                                        `FILTER_COUNT` bigint(20) DEFAULT NULL,
                                        `WRITE_COUNT` bigint(20) DEFAULT NULL,
                                        `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
                                        `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
                                        `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
                                        `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
                                        `EXIT_CODE` varchar(2500) DEFAULT NULL,
                                        `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
                                        `LAST_UPDATED` datetime(6) DEFAULT NULL,
                                        PRIMARY KEY (`STEP_EXECUTION_ID`),
                                        KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
                                        CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `test.BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.BATCH_STEP_EXECUTION_CONTEXT definition

CREATE TABLE `test.BATCH_STEP_EXECUTION_CONTEXT` (
                                                `STEP_EXECUTION_ID` bigint(20) NOT NULL,
                                                `SHORT_CONTEXT` varchar(2500) NOT NULL,
                                                `SERIALIZED_CONTEXT` text DEFAULT NULL,
                                                PRIMARY KEY (`STEP_EXECUTION_ID`),
                                                CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `test.BATCH_STEP_EXECUTION` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.diagnosis definition

CREATE TABLE `test.diagnosis` (
                             `diagnosis_id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `created_date` datetime(6) NOT NULL,
                             `updated_date` datetime(6) DEFAULT NULL,
                             `device_type` int(11) NOT NULL,
                             `image_url` varchar(255) DEFAULT NULL,
                             `scan_part` int(11) NOT NULL,
                             `value_1` int(11) DEFAULT NULL,
                             `value_2` int(11) DEFAULT NULL,
                             `value_3` int(11) DEFAULT NULL,
                             `value_4` int(11) DEFAULT NULL,
                             `value_5` int(11) DEFAULT NULL,
                             `value_6` int(11) DEFAULT NULL,
                             `member_id` bigint(20) NOT NULL,
                             PRIMARY KEY (`diagnosis_id`),
                             KEY `FKggaas8hghjptadq3vkhpydv9t` (`member_id`),
                             CONSTRAINT `FKggaas8hghjptadq3vkhpydv9t` FOREIGN KEY (`member_id`) REFERENCES `test.member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.examination_survey definition

CREATE TABLE `test.examination_survey` (
                                      `examination_survey_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `created_date` datetime(6) NOT NULL,
                                      `updated_date` datetime(6) DEFAULT NULL,
                                      `age` int(11) NOT NULL,
                                      `answer_1` varchar(30) NOT NULL,
                                      `answer_2` varchar(30) NOT NULL,
                                      `answer_3` varchar(30) NOT NULL,
                                      `answer_4` varchar(30) NOT NULL,
                                      `answer_5` varchar(30) NOT NULL,
                                      `answer_6` varchar(30) NOT NULL,
                                      `answer_7` varchar(30) NOT NULL,
                                      `gender` varchar(255) NOT NULL,
                                      `member_id` bigint(20) NOT NULL,
                                      PRIMARY KEY (`examination_survey_id`),
                                      KEY `FKd0elh9np80spjpverc4j2ei30` (`member_id`),
                                      CONSTRAINT `FKd0elh9np80spjpverc4j2ei30` FOREIGN KEY (`member_id`) REFERENCES `test.member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.member_routine definition

CREATE TABLE `test.member_routine` (
                                  `member_routine_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `created_date` datetime(6) NOT NULL,
                                  `updated_date` datetime(6) DEFAULT NULL,
                                  `is_notification_active` tinyint(1) DEFAULT 0,
                                  `notification_time` time DEFAULT NULL,
                                  `member_id` bigint(20) NOT NULL,
                                  `routine_id` bigint(20) NOT NULL,
                                  PRIMARY KEY (`member_routine_id`),
                                  UNIQUE KEY `UKfn82i2palln7d7j5khbti60u1` (`member_id`,`routine_id`),
                                  KEY `FK97qki7tcs8bcrjhy2qv654hsv` (`routine_id`),
                                  CONSTRAINT `FK97qki7tcs8bcrjhy2qv654hsv` FOREIGN KEY (`routine_id`) REFERENCES `test.routine` (`routine_id`),
                                  CONSTRAINT `FKirvx5axdv805ge3t05jl2lo9t` FOREIGN KEY (`member_id`) REFERENCES `test.member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.member_routine_log definition

CREATE TABLE `test.member_routine_log` (
                                      `member_routine_log_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `date` date NOT NULL,
                                      `is_done` tinyint(1) DEFAULT 0,
                                      `member_id` bigint(20) NOT NULL,
                                      `routine_id` bigint(20) NOT NULL,
                                      PRIMARY KEY (`member_routine_log_id`),
                                      UNIQUE KEY `UK96u6a3vdqe7ur491d034xbo5k` (`member_id`,`routine_id`,`date`),
                                      KEY `FKg15hpq4qf29voiq6fkl5ofdij` (`routine_id`),
                                      CONSTRAINT `FK4mxf8spsmuewtgdb69osmog6j` FOREIGN KEY (`member_id`) REFERENCES `test.member` (`member_id`),
                                      CONSTRAINT `FKg15hpq4qf29voiq6fkl5ofdij` FOREIGN KEY (`routine_id`) REFERENCES `test.routine` (`routine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.notification_configuration definition

CREATE TABLE `test.notification_configuration` (
                                              `notification_configuration_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                              `created_date` datetime(6) NOT NULL,
                                              `updated_date` datetime(6) DEFAULT NULL,
                                              `is_check_routine_active` tinyint(1) DEFAULT 0,
                                              `is_individual_routine_active` tinyint(1) DEFAULT 0,
                                              `member_id` bigint(20) NOT NULL,
                                              PRIMARY KEY (`notification_configuration_id`),
                                              UNIQUE KEY `unique_notification_configuration` (`member_id`),
                                              CONSTRAINT `FKh35hypbrgqsq8tka9ltqgf9sb` FOREIGN KEY (`member_id`) REFERENCES `test.member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.notification_token definition

CREATE TABLE `test.notification_token` (
                                      `notification_token_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `created_date` datetime(6) DEFAULT NULL,
                                      `device_info` varchar(255) NOT NULL,
                                      `refresh_date` datetime(6) DEFAULT NULL,
                                      `token` varchar(255) NOT NULL,
                                      `member_id` bigint(20) NOT NULL,
                                      PRIMARY KEY (`notification_token_id`),
                                      KEY `FKqld7c8jfn885g6opo7e8f864k` (`member_id`),
                                      CONSTRAINT `FKqld7c8jfn885g6opo7e8f864k` FOREIGN KEY (`member_id`) REFERENCES `test.member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- hanol.examination_result definition

CREATE TABLE `test.examination_result` (
                                      `examination_result_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `created_date` datetime(6) NOT NULL,
                                      `updated_date` datetime(6) DEFAULT NULL,
                                      `type_0` tinyint(1) NOT NULL,
                                      `type_1` tinyint(1) NOT NULL,
                                      `type_2` tinyint(1) NOT NULL,
                                      `type_3` tinyint(1) NOT NULL,
                                      `type_4` tinyint(1) NOT NULL,
                                      `type_5` tinyint(1) NOT NULL,
                                      `type_6` tinyint(1) NOT NULL,
                                      `examination_survey_id` bigint(20) NOT NULL,
                                      `member_id` bigint(20) NOT NULL,
                                      PRIMARY KEY (`examination_result_id`),
                                      KEY `FKe8x92lo7vx3yy70gvbp8jxyh7` (`examination_survey_id`),
                                      KEY `FKfn5ek2ag17qbdg5uuxltxr99q` (`member_id`),
                                      CONSTRAINT `FKe8x92lo7vx3yy70gvbp8jxyh7` FOREIGN KEY (`examination_survey_id`) REFERENCES `test.examination_survey` (`examination_survey_id`),
                                      CONSTRAINT `FKfn5ek2ag17qbdg5uuxltxr99q` FOREIGN KEY (`member_id`) REFERENCES `test.member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;