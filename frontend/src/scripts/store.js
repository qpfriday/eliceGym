import { createStore } from 'vuex'

// Create a new store instance.
const store = createStore({
    state () {
        return {
            account: {
                id: 0,
                name: '', // Add name property
                role: ''
            },
            isLoggedIn: false // 추가: 로그인 상태를 저장하는 상태 추가
        }
    },
    mutations: {
        setAccount(state, payload) {
            state.account.id = payload.id;
            state.account.name = payload.name; // Set name along with ID
            state.account.role = payload.role;
            state.isLoggedIn = true; // 추가: 로그인 상태 업데이트
        },
        resetAccount(state) {
            state.account.id = 0;
            state.account.name = '';
            state.account.role = '';
            state.isLoggedIn = false; // 추가: 로그인 상태 초기화
        }
    },
    getters: {
        isLoggedIn: state => state.isLoggedIn // 추가: 로그인 상태를 반환하는 게터
    }
})


export default store;
