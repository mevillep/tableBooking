/**
 * Created by Meville on 1/14/16.
 */

(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('TableCreateController', TableCreateController);

    TableCreateController.$inject = ['tabledataService'];

    function TableCreateController(tabledataService) {
        var tableCreateVm = this;


        tableCreateVm.register = function() {


            var user1 =angular.toJson(tableCreateVm.table);

            console.log(user1);


            tabledataService
                .createTable(user1)
                .then(function(data){
                    console.log(data);
                }, function (error){
                    console.log(error);
                })


        };




    }



})();