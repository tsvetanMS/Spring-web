const toString = ({ id, pictureID, description, userRole }) => {

    if(userRole == 'ADMIN') {
        let sectionAdmin = `<section class="section-two">
               <img src="https://drive.google.com/uc?export=view&id=${pictureID}" alt="Transistor picture" height="480" width="640"/>
               <p1>${description}</p1>
               </section>
               <aside class="aside-two">
                    <a href="/transistors/edit/${id}" class="btn-rounded btn btn-success w-25">Edit</a>
                    <a href="/transistors/delete/${id}"  class="btn-rounded btn btn-danger w-25">Delete</a>
                    <a href="/transistors/params/${id}"  class="btn-rounded btn btn-info w-25">Params</a>
               </aside>`

        return `<section class="section-one jumbotron">${sectionAdmin}</section>`
    } else {
        let sectionUser = `<section class="section-two">
               <img src="https://drive.google.com/uc?export=view&id=${pictureID}" alt="Transistor picture" height="480" width="640"/>
               <p1>${description}</p1>
               </section>
               <aside class="aside-two">
                        <a href="/transistors/params/${id}"  class="btn-rounded btn btn-info w-25">Params</a>
               </aside>`

        return `<section class="section-one jumbotron">${sectionUser}</section>`
    }


};


fetch('/fetch/transistors')
    .then(response => response.json())
    .then(transistors => {

        let result = '';

        transistors.forEach(transistor => {
            const transistorString = toString(transistor);
            result += transistorString;
        });

        document.getElementById('put-all-transistors-here')
            .innerHTML=result;
    });
