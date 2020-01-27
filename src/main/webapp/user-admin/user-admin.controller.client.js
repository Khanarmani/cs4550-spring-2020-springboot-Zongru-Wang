(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();

    let $userList

    let users = [
        {"username": 'alice',
         "password": '%$6646123',
         "firstName": 'Ash',
         "lastName": 'N',
            "role": 'FACULTY'}
    ]




    function createUser() {
        const newUser = new User();
        newUser.setUsername($usernameFld.val());
        newUser.setPassword($passwordFld.val());
        newUser.setFirstName($firstNameFld.val());
        newUser.setLastName($lastNameFld.val());
        newUser.setRole($roleFld.val())



        $usernameFld.val("")

        $firstNameFld.val("")
        $lastNameFld.val("")
        $roleFld.val("")

        userService.createUser(newUser)
            .then((actualUser) => {
                 // users.push(actualUser)
                 // renderUsers()
                findAllUsers()
            })



    }


    function findAllUsers() {
        userService
            .findAllUsers()
            .then(theusers => {
                users = theusers
                renderUsers()
            })
    }


    function findUserById() {  }


    function deleteUser() {  }


    function selectUser() {  }


    function updateUser() {  }

    function renderUser(user) {



    }

    function renderUsers(users) {


        for(let u in users) {
            let user = users[u]

            $removeBtn = $("<button>Delete</button>")
            $removeBtn.click(() => deleteUser(u))

            $editBtn = $("<button>Edit</button>")
            $editBtn.click(() => editUser(u))

            $tr = $("<tr class=\"wbdv-template wbdv-user wbdv-hidden\">")
            $td = $("<td>")
            $td.append(user.username)
            $td.append(user.password)
            $td.append(user.firstName)
            $td.append(user.lastName)
            $td.append(user.role)

            $span = $("<span class=\"float-right\">")

            $span.append($removeBtn)
            $span.append($editBtn)
            $td.append($span)
            $userList.append($li)
        }


    }

    function main() {
        $userList = $("#userList")
        $usernameFld = $("#usernameFld")
        $firstNameFld = $("#firstNameFld")
        $lastNameFld = $("#lastNameFld")
        $roleFld = $("#roleFld")

        $createBtn = $("#createBtn")
        $createBtn.click(createUser)

        userService
            .findAllUsers()
            .then(theusers => {
                users = theusers
                renderUsers()
            })
    }

    $(main)

})();
