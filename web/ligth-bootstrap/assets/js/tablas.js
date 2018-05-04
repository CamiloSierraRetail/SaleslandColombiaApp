function botstrapPaginacionTabla(tamanio,showRefresh, search, showToggle, showColumns, alineacion){
    var $table = $('#bootstrap-table');

    $().ready(function() {
        
        $table.bootstrapTable({
            toolbar: ".toolbar",
            clickToSelect: true,
            showRefresh: showRefresh,
            search: search,
            showToggle: showToggle,
            showColumns: showColumns,
            pagination: true,
            searchAlign: alineacion,
            pageSize: tamanio,
            clickToSelect: false,
            pageList: [5, 10, 25, 50, 100],

            formatShowingRows: function(pageFrom, pageTo, totalRows) {
                //do nothing here, we don't want to show the text "showing x of y from..."
            },
            formatRecordsPerPage: function(pageNumber) {
                return pageNumber + 'Elementos visibles';
            },
            icons: {
                refresh: 'fa fa-refresh',
                toggle: 'fa fa-th-list',
                columns: 'fa fa-columns',
                detailOpen: 'fa fa-plus-circle',
                detailClose: 'fa fa-minus-circle'
            }
        });

        //activate the tooltips after the data table is initialized
        $('[rel="tooltip"]').tooltip();

        $(window).resize(function() {
            $table.bootstrapTable('resetView');
        });

    });
    
}

