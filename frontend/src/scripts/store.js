import { createStore } from 'vuex'

// Create a new store instance.
const store = createStore({
    state () {
        return {
            account: {
                id: 0,
                name: '' // Add name property
            }
        }
    },
    mutations: {
        setAccount(state, payload) {
            state.account.id = payload.id;
            state.account.name = payload.name; // Set name along with ID
        },
        resetAccount(state) {
            state.account.id = 0;
            state.account.name = '';
        }
    }
})

export default store;
