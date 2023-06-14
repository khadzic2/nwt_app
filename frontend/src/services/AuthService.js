const LOCAL_STORAGE_KEY = "loggedUser";

class AuthService {
    login(username,password){
        let role="customer";
        if(username==="Admin"){
            role="admin"
        }
        localStorage.setItem(LOCAL_STORAGE_KEY,JSON.stringify({username:username,password:password,role:role}));

        // return axios.post(API_URL + "login", {
        //     username: username,
        //     password: password
        // }).then(response => {
        //     if (response.data.accessToken) {
        //         const token = response.data.accessToken;
        //         console.log(token);
        //         const tokenData = jwt(token);
        //         console.log(tokenData.sub);
        //         localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify({userId: tokenData.sub, token: token}));
        //     }
        //     return true;
        // }).catch(err => {
        //     console.warn(err);
        //     return false;
        // });
    }

    getCurrentUser(){
        return JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY));
    }

    logout(){
        localStorage.removeItem(LOCAL_STORAGE_KEY);
    }
}

export default new AuthService();