var database = {users: ["CSSer", "John", "David"],
    sendEmail: function (user,t2) {if (this.isValidUser(user)) {
        console.log("test");
    }},
    isValidUser: function (user) {
        console.log("test1");
        return true;
    }};
// 向每个用户发送邮件 
database.users.forEach(database.sendEmail,database);