
const toString = ({ username, email }) => {
    let columns = `
    <td>${username}</td>
    <td>${email}</td>
    `
    return `<tr>${columns}</tr>>`
};


fetch('/api/planets')
    .then(response => response.json())
    .then(users => {
        let result = '';
        users.forEach(user => {
            const userString = toString(user);
            result += userString;
        });
        document.getElementById('users-table')
            .innerHTML=result;
    });
