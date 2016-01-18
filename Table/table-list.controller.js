/**
 * Created by Meville on 1/14/16.
 */


(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('TableListController', TableListController);

    TableListController.$inject = ['tabledataService'];

    function TableListController(tabledataService) {
        var tableListVm = this;

        tableListVm.tables = [];



        tabledataService.getTables()
            .then(function(data) {
                tableListVm.tables = data;
        }, function(error) {
            console.log(error);
        });

    }
})();