import { computed } from "vue";

export function usePagination(state) {
    const displayedItems = computed(() => {
        const startIndex = (state.currentPage - 1) * state.itemsPerPage;
        const endIndex = startIndex + state.itemsPerPage;
        return state.items.slice(startIndex, endIndex);
    });

    const totalPages = computed(() => Math.ceil(state.items.length / state.itemsPerPage));

    const visiblePages = computed(() => {
        let startPage = Math.max(1, state.currentPage - Math.floor(state.maxVisiblePages / 2));
        let endPage = Math.min(totalPages.value, startPage + state.maxVisiblePages - 1);
        if (endPage - startPage + 1 < state.maxVisiblePages) {
            startPage = Math.max(1, endPage - state.maxVisiblePages + 1);
        }
        return Array.from({ length: endPage - startPage + 1 }, (_, i) => startPage + i);
    });

    const changePage = (pageNumber) => {
        state.currentPage = pageNumber;
    };

    const nextPage = () => {
        if (state.currentPage < totalPages.value) {
            state.currentPage++;
        }
    };

    const prevPage = () => {
        if (state.currentPage > 1) {
            state.currentPage--;
        }
    };

    return { displayedItems, totalPages, visiblePages, changePage, nextPage, prevPage };
}