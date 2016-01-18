/**
 * Created by Meville on 1/16/16.
 */

(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('TableDetailController', TableDetailController);

    TableDetailController.$inject = ['tabledataService', '$routeParams'];

    function TableDetailController(tabledataService, $routeParams) {
        var tableDetailVm = this;

        tableDetailVm.detail = null;

        tabledataService
            .getTableById($routeParams.id)
            .then(function (data) {
                tableDetailVm.detail = data;
            }, function (error) {
                console.log(error);
            })



        tableDetailVm.update = function ()
        {


            var table1 = angular.toJson(tableDetailVm.detail);

            console.log(table1);


            tabledataService
                .updateTable($routeParams.id,table1)
                .then(function (data) {
                    console.log(data);
                }, function (error) {
                    console.log(error);
                })


        }


    }


})();