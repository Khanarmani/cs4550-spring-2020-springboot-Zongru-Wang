(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $userRowTemplate, $tbody;
    var $updateBtn;
    var userService = new AdminUserServiceClient();



    let users = [];


    function createUser() {
        const newUser = new User();
        newUser.setUsername($usernameFld.val());
        newUser.setPassword($passwordFld.val());
        newUser.setFirstName($firstNameFld.val());
        newUser.setLastName($lastNameFld.val());
        newUser.setRole($roleFld.val());

        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("FACULTY");

        userService.createUser(newUser)
            .then((actualUser) => {
                findAllUsers()
            })

    }


    function findAllUsers() {
        userService
            .findAllUsers()
            .then(theusers => {
                users = theusers
                renderUsers(theusers)
            })
    }


    function findUserById(id) {
        userService.findUserById(id)
            .then((actualuser) => {
                renderUser(actualuser)
            })
    }


    function deleteUser(index) {
        let user = users[index];
        let userId = user._id;

        userService.deleteUser(userId)
            .then(response => {
                users.splice(index, 1);
                renderUsers(users);
            })
    }

    let currentUserIndex = -1;

    function selectUser(userIndex) {
        currentUserIndex = userIndex;
        let user = users[userIndex];
        let userid = user._id;

        userService
            .findUserById(userid)
            .then(actucalUser => {
                $usernameFld.val(actucalUser.username);
                $("#passwordFld").val("********");
                $firstNameFld.val(actucalUser.firstName);
                $lastNameFld.val(actucalUser.lastName);
                $roleFld.val(actucalUser.role);
            })

    }


    function updateUser() {
        const updateUser = new User();
        updateUser.setUsername($usernameFld.val());
        updateUser.setLastName($lastNameFld.val());
        updateUser.setFirstName($firstNameFld.val());
        updateUser.setPassword($passwordFld.val());
        updateUser.setRole($roleFld.val());

        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");

        updateUser._id = users[currentUserIndex]._id;

        userService.updateUser(updateUser._id, updateUser)
            .then((actualUser) => {
                findAllUsers()
            })
    }

    function renderUser(user) {

        $tr = $("<tr class=\"wbdv-user wbdv-hidden\">");
        $tr.append("<td class='wbdv-username'>" + user.username +"</td>>");
        $tr.append("<td>&nbsp;</td>");
        $tr.append("<td class='wbdv-first-name'>" + user.firstName + "</td>");
        $tr.append("<td class='wbdv-last-name'>" + user.lastName + "</td>");
        $tr.append("<td class='wbdv-role'>" + user.role + "</td>");
        $span = $("<span class=\"float-right\">");
        $span.append("<i id=\"wbdv-remove-" + user._id
            + "\" class=\"btn fa-2x fa fa-times wbdv-remove\"></i>");

        $span.append("<i id=\"wbdv-edit-" + user._id
            + "\" class=\"btn fa-2x fa fa-pencil wbdv-edit\"></i>");
        $tr.append($span);
        $tbody.append($tr);


    }

    function renderUsers(users) {
        $tbody.empty();
        for(let u in users) {
            const user = users[u];
            renderUser(user);

            $removeBtn = $("#wbdv-remove-" + user._id);
            $removeBtn.click(() => deleteUser(u));
            $editBtn = $("#wbdv-edit-" + user._id);
            $editBtn.click(() => selectUser(u));

        }

    }

    function main() {
        $tbody = $("#tbody");

        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");
        $updateBtn = $("#updateButton");
        $updateBtn.click(updateUser);


        $createBtn = $("#createBtn");
        $createBtn.click(createUser);



        userService
            .findAllUsers()
            .then(theusers => {
                users = theusers;
                renderUsers(users)
            })
    }

    $(main)

})();