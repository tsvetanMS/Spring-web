const toString = ({ id, username, email, userRole }) => {

    if(userRole == 'ADMIN') {
        let sectionAdmin = `<section class = "section-two">
               <p1>Username - ${username}</p1>
               <p1>Email - ${email}</p1>
               <p1>Role - ${userRole}</p1>
               </section>
               `

        return `<section class="jumbotron">${sectionAdmin}</section>`
    } else {
        let sectionUser = `<section class = "section-two">
               <p1>Username -> ${username}</p1>
               <p1>     Email -> ${email}</p1>
               <p1>     Role -> ${userRole}</p1>
               </section>
               <aside class = "aside-three">
               <a href="/users/promote/${id}" class="btn-rounded btn btn-success w-25">Promote to Admin</a>
               <a href="/users/delete/${id}"  class="btn-rounded btn btn-danger w-25">Delete User</a>   
               </aside>
               `

        return `<section class="jumbotron">${sectionUser}</section>`
    }


};


fetch('/users/edit/api')
    .then(response => response.json())
    .then(users => {

        let result = '';

        users.forEach(user => {
            const userString = toString(user);
            result += userString;
        });

        document.getElementById('put-all-users-here')
            .innerHTML = result;
    });
