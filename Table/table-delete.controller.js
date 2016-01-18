
/**
 * Created by Meville on 1/14/16.
 */
(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('TableDeleteController', TableDeleteController);

    TableDeleteController.$inject = ['tabledataService', '$routeParams'];

    function TableDeleteController(tabledataService, $routeParams) {
        var tableDeleteVm = this;

        tableDeleteVm.detail = null;

        tabledataService
            .deleteTable($routeParams.id)
            .then(function(data){
                tableDeleteVm.detail = data;
            }, function (error){
                console.log(error);
            })

    }
})();

