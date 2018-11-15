function UsersView() {

}

UsersView.prototype.renderUsersView = function (users, currentUser) {
    var that = this;

    var elements = document.createElement('div');
    elements.classList.add("list-group");
    console.log("SADADDSADSDADASD" );
    console.log(elements);
    users.forEach(function (user) {
        elements.appendChild(that.renderElement(user));
    });


    return elements;
};

UsersView.prototype.renderElement = function (user) {

    var element = document.createElement('a');
    element.href = '#';
    element.classList.add('list-group-item');
    element.textContent = user.getName();
    if (user.getUserRole().toUpperCase() === 'ADMIN'.toUpperCase()) {
        element.classList.add('list-group-item-danger');
    } else if (user.getUserRole().toUpperCase() === 'USER'.toUpperCase()) {
        element.classList.add('list-group-item-success');
    }
    return element;
};