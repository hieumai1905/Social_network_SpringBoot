package com.example.socialnetwork.models.enums;

public enum ErrorMessage {
    OK(ErrorCode.OK, "Success"), INTERNAL_SERVER_ERROR(ErrorCode.INTERNAL_SERVER_ERROR, "Internal server error"),
    INVALID_TOKEN(ErrorCode.INVALID_TOKEN, "Invalid token"),
    INVALID_ID(ErrorCode.INVALID_ID, "Invalid Id"),
    INVALID_PROJECT_RANK_ID(ErrorCode.IVALID_PROJECT_RANK_ID, "Invalid project rank id"),
    INVALID_PROJECT_RANK_NAME(ErrorCode.IVALID_PROJECT_RANK_NAME, "Invalid project rank name"),
    INVALID_PROJECT_TYPE_NAME(ErrorCode.IVALID_PROJECT_TYPE_NAME, "Invalid project type name"),

    INVALID_BODY(ErrorCode.INVALID_BODY, "Invalid Body"),
    INVALID_HEADER_PARAM(ErrorCode.INVALID_HEADER_PARAM, "Invalid Header"),
    INVALID_REQUEST_PARAM(ErrorCode.INVALID_REQUEST_PARAM, "Invalid Request Param"),
    INVALID_PART_PARAM(ErrorCode.INVALID_PART_PARAM, "Invalid Part Param"),
    IVALID_PROJECT_TYPE_NAME(ErrorCode.IVALID_PROJECT_TYPE_NAME, "Invalid project type name"),
    PERMISSION_DENIED(ErrorCode.PERMISSION_DENIED, "Permission denied"),
    INVALID_PROJECT_TYPE_ID(ErrorCode.IVALID_PROJECT_TYPE_ID, "Invalid project type id"),
    INVALID_SERVICE_ID(ErrorCode.INVALID_SERVICE_ID, "Invalid Service Id"),

    INVALID_FILE_ID(ErrorCode.INVALID_FILE_ID, "Invalid File Id"),
    INVALID_USER_ID(ErrorCode.INVALID_USER_ID, "Invalid User Id"),
    INVALID_STAGE_ID(ErrorCode.INVALID_STAGE_ID, "Invalid Stage Id"),
    INVALID_UNIT_OF_EFFORT_ID(ErrorCode.INVALID_UNIT_OF_EFFORT_ID, "Invalid Unit Of Effort"),

    INVALID_MEDIA_TYPE(ErrorCode.INVALID_MEDIA_TYPE, "Invalid Media Type"),

    SEND_MAIL_ERROR(ErrorCode.SEND_MAIL_ERROR, "Send email error"),
    INVALID_JSON_PARSE(ErrorCode.INVALID_JSON_PARSE, "Invalid Json Object"),

    FILE_NOT_EXISTS(ErrorCode.FILE_NOT_EXISTS, "File not exists"),
    FILE_NOT_READABLE(ErrorCode.FILE_NOT_READABLE, "File not readable"),
    INVALID_USERNAME(ErrorCode.INVALID_USERNAME, "Invalid Username"),

    INVALID_DEPARTMENT_ID(ErrorCode.INVALID_DEPARTMENT_ID, "Invalid Department ID"),
    INVALID_COMPANY_ID(ErrorCode.INVALID_COMPANY_ID, "Invalid Company ID"),
    INVALID_PROJECT_CODE(ErrorCode.INVALID_PROJECT_CODE, "Invalid Project Code"),
    INVALID_CONTRACT_ID(ErrorCode.INVALID_CONTRACT_ID, "Invalid Contract ID"),

    INVALID_REQUIRED_FIELD(ErrorCode.INVALID_REQUIRED_FIELD, "Invalid field required"),
    INVALID_PROJECT_ID(ErrorCode.INVALID_PROJECT_ID, "Invalid Project ID"),
    INVALID_APPROVER_USERNAME(ErrorCode.INVALID_APPROVER_USERNAME, "Invalid username approver"),
    INVALID_ROLE_ID(ErrorCode.INVALID_ROLE_ID, "Invalid role ID"),
    INVALID_LEVEL_ID(ErrorCode.INVALID_LEVEL_ID, "Invalid level ID"),
    INVALID_SKILL_ID(ErrorCode.INVALID_SKILL_ID, "Invalid skill ID"),
    INVALID_ACCOUNT_ID(ErrorCode.INVALID_ACCOUNT_ID, "Invalid account ID"),
    INVALID_PROJECT_NAME(ErrorCode.INVALID_PROJECT_NAME, "Invalid Project Name"),
    INVALID_STAGE_NAME(ErrorCode.INVALID_STAGE_NAME, "Invalid Stage Name"),

    PLAN_NOT_APPROVED(ErrorCode.PLAN_NOT_APPROVED, "Plan is not approved"),
    INVALID_PROJECT_TYPE(ErrorCode.INVALID_PROJECT_TYPE, "Invalid project type"),
    INVALID_PROJECT_STATUS(ErrorCode.INVALID_PROJECT_STATUS, "Invalid project status"),
    INVALID_START_DATE(ErrorCode.INVALID_START_DATE, "Invalid Start Date"),
    INVALID_END_DATE(ErrorCode.INVALID_END_DATE, "Invalid End Date"),
    INVALID_DATE(ErrorCode.INVALID_DATE, "Invalid Start Date and End Date"),
    INVALID_RESOURCE_PLANNING_ID(ErrorCode.INVALID_RESOURCE_PLANNING_ID, "Invalid Resource Planning ID"),
    INVALID_RESOURCE_ALLOCATED_ID(ErrorCode.INVALID_RESOURCE_ALLOCATED_ID, "Invalid Resource Allocated ID"),
    INVALID_SPECIFIC_RESOURCE_REQUEST(ErrorCode.INVALID_SPECIFIC_RESOURCE_REQUEST, "Request must be general, not specific"),
    INVALID_GENERAL_RESOURCE_REQUEST(ErrorCode.INVALID_GENERAL_RESOURCE_REQUEST, "Request must be specific, not general"),
    STATUS_RESOURCE_PLANNING_CHANGE_FAILED(ErrorCode.STATUS_RESOURCE_PLANNING_CHANGE_FAILED, "Status resource planning can't change"),
    INVALID_CLIENT_NAME(ErrorCode.INVALID_CLIENT_NAME, "Invalid Client Name"),
    INVALID_PRIORITY(ErrorCode.INVALID_PRIORITY, "Invalid Priority"),
    INVALID_ADD_EMPLOYEE(ErrorCode.INVALID_ADD_EMPLOYEE, "Invalid Add Employee, Employee has been in planning or allocated"),
    INVALID_CONNECTION_TO_PROFILE_SERVICE(ErrorCode.INVALID_CONNECTION_TO_PROFILE_SERVICE, "Cann't connect to Profile Service"),
    INVALID_PERIODID_AND_PROJECTID(ErrorCode.INVALID_PERIODID_AND_PROJECTID, "input error"),
    INVALID_SEARCH_NAME(ErrorCode.INVALID_SEARCH_NAME, "Search name error"),
    INVALID_EVALUATION_LIST_PROJECT(ErrorCode.INVALID_EVALUATION_LIST_PROJECT, "Invalid project list"),
    INVALID_EVALUATION_STATUS(ErrorCode.INVALID_EVALUATION_STATUS, "Invalid evaluation status"),
    INVALID_EVALUATION_USERNAME(ErrorCode.INVALID_EVALUATION_USERNAME, "Invalid evaluation username"),
    INVALID_EVALUATION_PERIOD_ID(ErrorCode.INVALID_EVALUATION_PERIOD_ID, "Invalid evaluation Period Id"),
    INVALID_EVALUATION_PROJECT_ID(ErrorCode.INVALID_EVALUATION_PROJECT_ID, "Invalid evaluation Project Id"),
    INVALID_EVALUATION_LIST_ID(ErrorCode.INVALID_EVALUATION_LIST_ID, "Invalid evaluation list Id"),
    INVALID_EVALUATION_DISCIPLINE(ErrorCode.INVALID_EVALUATION_DISCIPLINE, "Invalid evaluation discipline"),
    INVALID_EVALUATION_WORK_EFFICIENCY(ErrorCode.INVALID_EVALUATION_WORK_EFFICIENCY, "Invalid evaluation discipline"),
    INVALID_EVALUATION_PATICIPATE(ErrorCode.INVALID_EVALUATION_PATICIPATE, "Invalid evaluation discipline"),
    INVALID_EVALUATION_CONTRIBUTE_LEVEL(ErrorCode.INVALID_EVALUATION_CONTRIBUTE_LEVEL, "Invalid evaluation discipline"),
    INVALID_EVALUATION_RESULT(ErrorCode.INVALID_EVALUATION_RESULT, "Invalid evaluation discipline"),
    INVALID_EVALUATION_DATE_ACTUALY(ErrorCode.INVALID_EVALUATION_DATE_ACTUALY, "Invalid evaluation discipline"),
    INVALID_EVALUATION_RATE(ErrorCode.INVALID_EVALUATION_RATE, "Invalid evaluation rate"),
    INVALID_EVALUATION_MESSAGE(ErrorCode.INVALID_EVALUATION_MESSAGE, "Invalid evaluation message"),
    UNKNOWN_ERROR(ErrorCode.UNKNOWN_ERROR, "Unknown error"),
    INVALID_STOP_DATE(ErrorCode.INVALID_STOP_DATE, "Invalid Stop Date"),
    INVALID_ROLE_NAME(ErrorCode.INVALID_ROLE_NAME, "Invalid Role Name"),
    INVALID_ROLE_CODE(ErrorCode.INVALID_ROLE_CODE, "Invalid Role Code"),
    INVALID_DESCRIPTION(ErrorCode.INVALID_DESCRIPTION, "Invalid Description"),
    ACCOUNT_HAS_BEEN_ROLE(ErrorCode.ACCOUNT_HAS_BEEN_ROLE, "Account has been this role"),
    ACCOUNT_HAS_NOT_ROLE(ErrorCode.ACCOUNT_HAS_NOT_ROLE, "Account has not have this role"),
    ACCOUNT_HAS_BEEN_SKILL(ErrorCode.ACCOUNT_HAS_BEEN_SKILL, "Account has been this skill"),
    ACCOUNT_HAS_NOT_SKILL(ErrorCode.ACCOUNT_HAS_NOT_SKILL, "Account has not have this skill"),
    INVALID_SKILL_NAME(ErrorCode.INVALID_SKILL_NAME, "Invalid Skill Name"),
    INVALID_LEVEL_NAME(ErrorCode.INVALID_LEVEL_NAME, "Invalid Level Name"),
    SKILL_NAME_EXISTED(ErrorCode.SKILL_NAME_EXISTED, "Skill name is existed"),

    UNIT_OF_EFFORT_ID_EXISTED(ErrorCode.UNIT_OF_EFFORT_ID_EXISTED, "Unit Of Effort is existed"),

    UNIT_OF_EFFORT_NAME_EXISTED(ErrorCode.UNIT_OF_EFFORT_NAME_EXISTED, "Unit of effort name is existed"),
    LEVEL_NAME_EXISTED(ErrorCode.LEVEL_NAME_EXISTED, "Level name is existed"),
    STAGE_NAME_EXISTED(ErrorCode.STAGE_NAME_EXISTED, "Stage name is existed"),
    ROLE_NAME_EXISTED(ErrorCode.ROLE_NAME_EXISTED, "Role name is existed"),
    ROLE_CODE_EXISTED(ErrorCode.ROLE_CODE_EXISTED, "Role code is existed"),
    CONTRACT_TYPE_NAME_EXISTED(ErrorCode.CONTRACT_TYPE_NAME_EXISTED, "Contract type name is existed"),
    PROJECT_NAME_EXISTED(ErrorCode.PROJECT_NAME_EXISTED, "Project name is existed"),
    PROJECT_CODE_EXISTED(ErrorCode.PROJECT_CODE_EXISTED, "Project Code is existed"),
    CONTRACT_TYPE_ID_EXISTED(ErrorCode.CONTRACT_TYPE_ID_EXISTED, "Project Code is existed"),
    INVALID_CONTRACT_TYPE(ErrorCode.INVALID_CONTRACT_TYPE, "Invalid contract type"),
    INVALID_CONTRACT_TYPE_NAME(ErrorCode.INVALID_CONTRACT_TYPE_NAME, "Invalid contract type name"),

    INVALID_UNIT_OF_EFFORT(ErrorCode.INVALID_UNIT_OF_EFFORT, "Invalid contract type"),
    IVALID_PROJECT_RANK(ErrorCode.IVALID_PROJECT_RANK, "Invalid contract type"),

    ;


    private final int code;
    private final String message;

    ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorMessage valueOf(int i) {
        for (ErrorMessage msg : ErrorMessage.values()) {
            if (msg.code == i) {
                return msg;
            }
        }
        return null;
    }

    public static boolean isOK(ErrorMessage er) {
        return ErrorMessage.OK.equals(er);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("Code: %d, Message: %s", this.code, this.message);
    }

    public static class ErrorCode {
        public static final int BASE = 105000;
        public static final int OK = BASE;
        public static final int INTERNAL_SERVER_ERROR = BASE + 1;
        public static final int INVALID_TOKEN = BASE + 2;
        public static final int INVALID_ID = BASE + 3;
        public static final int INVALID_BODY = BASE + 4;
        public static final int INVALID_HEADER_PARAM = BASE + 5;
        public static final int INVALID_REQUEST_PARAM = BASE + 6;
        public static final int INVALID_PART_PARAM = BASE + 7;
        public static final int PERMISSION_DENIED = BASE + 8;
        public static final int INVALID_FILE_ID = BASE + 13;
        public static final int INVALID_USER_ID = BASE + 15;
        public static final int INVALID_MEDIA_TYPE = BASE + 16;
        public static final int SEND_MAIL_ERROR = BASE + 19;
        public static final int INVALID_JSON_PARSE = BASE + 20;
        public static final int FILE_NOT_EXISTS = BASE + 21;
        public static final int FILE_NOT_READABLE = BASE + 22;
        public static final int INVALID_USERNAME = BASE + 23;
        public static final int INVALID_SERVICE_ID = BASE + 24;
        public static final int INVALID_REQUIRED_FIELD = BASE + 100;
        public static final int INVALID_PROJECT_CODE = BASE + 101;
        public static final int INVALID_PROJECT_ID = BASE + 102;
        public static final int INVALID_APPROVER_USERNAME = BASE + 103;
        public static final int INVALID_DEPARTMENT_ID = BASE + 104;
        public static final int INVALID_ROLE_ID = BASE + 105;
        public static final int INVALID_LEVEL_ID = BASE + 106;
        public static final int INVALID_SKILL_ID = BASE + 107;
        public static final int INVALID_ACCOUNT_ID = BASE + 108;
        public static final int INVALID_START_DATE = BASE + 109;
        public static final int INVALID_END_DATE = BASE + 110;
        public static final int INVALID_RESOURCE_PLANNING_ID = BASE + 111;
        public static final int STATUS_RESOURCE_PLANNING_CHANGE_FAILED = BASE + 112;
        public static final int INVALID_SPECIFIC_RESOURCE_REQUEST = BASE + 113;
        public static final int INVALID_GENERAL_RESOURCE_REQUEST = BASE + 114;
        public static final int INVALID_RESOURCE_ALLOCATED_ID = BASE + 115;
        public static final int INVALID_CLIENT_NAME = BASE + 116;
        public static final int INVALID_PRIORITY = 117;
        public static final int INVALID_ADD_EMPLOYEE = BASE + 118;

        public static final int INVALID_STOP_DATE = BASE + 119;
        public static final int INVALID_PROJECT_NAME = BASE + 120;

        public static final int PLAN_NOT_APPROVED = BASE + 121;
        public static final int INVALID_PROJECT_TYPE = BASE + 122;
        public static final int INVALID_PROJECT_STATUS = BASE + 123;
        public static final int INVALID_DATE = BASE + 124;
        public static final int INVALID_COMPANY_ID = BASE + 125;
        public static final int PROJECT_CODE_EXISTED = BASE + 126;
        public static final int INVALID_CONTRACT_TYPE = BASE + 127;
        public static final int INVALID_UNIT_OF_EFFORT = BASE + 128;
        public static final int IVALID_PROJECT_RANK = BASE + 129;
        public static final int IVALID_PROJECT_RANK_ID = BASE + 130;
        public static final int IVALID_PROJECT_RANK_NAME = BASE + 131;
        public static final int INVALID_PROJECT_RANK_STATUS = BASE + 132;
        public static final int IVALID_PROJECT_TYPE_ID = BASE + 133;
        public static final int IVALID_PROJECT_TYPE_NAME = BASE + 133;

        public static final int INVALID_EVALUATION_PERIOD_ID = BASE + 180;
        public static final int INVALID_EVALUATION_PROJECT_ID = BASE + 181;
        public static final int INVALID_PERIODID_AND_PROJECTID = BASE + 182;
        public static final int INVALID_SEARCH_NAME = BASE + 183;
        public static final int INVALID_EVALUATION_LIST_PROJECT = BASE + 184;
        public static final int INVALID_EVALUATION_STATUS = BASE + 185;
        public static final int INVALID_EVALUATION_USERNAME = BASE + 186;
        public static final int INVALID_EVALUATION_LIST_ID = BASE + 187;
        public static final int INVALID_EVALUATION_DISCIPLINE = BASE + 188;
        public static final int INVALID_EVALUATION_WORK_EFFICIENCY = BASE + 189;
        public static final int INVALID_EVALUATION_PATICIPATE = BASE + 190;
        public static final int INVALID_EVALUATION_CONTRIBUTE_LEVEL = BASE + 191;
        public static final int INVALID_EVALUATION_RESULT = BASE + 192;
        public static final int INVALID_EVALUATION_DATE_ACTUALY = BASE + 193;
        public static final int INVALID_EVALUATION_RATE = BASE + 194;
        public static final int INVALID_EVALUATION_MESSAGE = BASE + 195;


        public static final int INVALID_CONNECTION_TO_PROFILE_SERVICE = BASE + 900;
        public static final int UNKNOWN_ERROR = BASE + 999;
        public static final int INVALID_ROLE_NAME = BASE + 300;
        public static final int INVALID_ROLE_CODE = BASE + 301;
        public static final int INVALID_DESCRIPTION = BASE + 302;
        public static final int ACCOUNT_HAS_BEEN_ROLE = BASE + 303;
        public static final int ACCOUNT_HAS_NOT_ROLE = BASE + 304;
        public static final int ACCOUNT_HAS_BEEN_SKILL = BASE + 305;
        public static final int ACCOUNT_HAS_NOT_SKILL = BASE + 306;
        public static final int INVALID_SKILL_NAME = BASE + 307;
        public static final int INVALID_LEVEL_NAME = BASE + 308;
        public static final int SKILL_NAME_EXISTED = BASE + 309;
        public static final int LEVEL_NAME_EXISTED = BASE + 310;
        public static final int ROLE_NAME_EXISTED = BASE + 311;
        public static final int PROJECT_NAME_EXISTED = BASE + 312;
        public static final int ROLE_CODE_EXISTED = BASE + 313;
        public static final int CONTRACT_TYPE_NAME_EXISTED = BASE + 314;
        public static final int INVALID_CONTRACT_ID = BASE + 315;
        public static final int CONTRACT_TYPE_ID_EXISTED = BASE + 316;
        public static final int INVALID_STAGE_NAME = BASE + 317;
        public static final int INVALID_STAGE_ID = BASE + 318;
        public static final int INVALID_CONTRACT_TYPE_NAME = BASE + 319;
        public static final int STAGE_NAME_EXISTED = BASE + 320;
        public static final int UNIT_OF_EFFORT_NAME_EXISTED = BASE + 321;
        public static final int INVALID_UNIT_OF_EFFORT_ID = BASE + 322;
        public static final int UNIT_OF_EFFORT_ID_EXISTED = BASE + 323;

        private ErrorCode() {
        }

    }
}
