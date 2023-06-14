class UserService{
    static currentUser = null;

    getUser() {
        return UserService.currentUser;
    }
}

export default new  UserService();