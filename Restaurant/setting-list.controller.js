/**
 * Created by Meville on 1/16/16.
 */
/**
 * Created by Meville on 1/14/16.
 */


(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('SettingListController', SettingListController);

    SettingListController.$inject = ['settingdataService'];

    function SettingListController(settingdataService) {
        var settingListVm = this;

        settingListVm.setting = null;



        settingdataService.getSetting()
            .then(function(data) {
                settingListVm.setting = data;
            }, function(error) {
                console.log(error);
            });


        settingListVm.update = function ()
        {


            var setting = angular.toJson(settingListVm.setting);

            console.log("This is in control"+ setting);
            var id = 1;
            settingdataService
                .updateSetting(id ,setting)
                .then(function (data) {
                    console.log(data);
                }, function (error) {
                    console.log(error);
                })


        }

    }
})();