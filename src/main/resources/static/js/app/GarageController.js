'use strict';

angular.module('crudApp').controller('GarageController',
    ['GarageService', '$scope',  function( GarageService, $scope) {

        var self = this;
        self.garage = {};
        self.garages=[];

        self.submit = submit;
        self.getAllGarages = getAllGarages;
        self.createGarage = createGarage;
        self.updateGarage = updateGarage;
        self.removeGarage = removeGarage;
        self.editGarage = editGarage;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Validation');
            if (self.garage.id === undefined || self.garage.id === null) {
                console.log('Enregistrement du nouveau Garage', self.garage);
                createGarage(self.garage);
            } else {
                updateGarage(self.garage, self.garage.id);
                console.log('Garage mis à jour avec l''id ', self.garage.id);
            }
        }

        function createGarage(garage) {
            GarageService.createGarage(garage)
                .then(
                    function (response) {
                        console.log('Garage créé');
                        self.successMessage = 'Garage créé';
                        self.errorMessage='';
                        self.done = true;
                        self.garage={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Erreur lors de la création du Garage');
                        self.errorMessage = 'Erreur lors de la création du Garage: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateGarage(garage, id){
            GarageService.updateGarage(garage, id)
                .then(
                    function (response){
                        console.log('Garage mis à jour');
                        self.successMessage='Garage mis à jour';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Erreur lors de la mise à jour du Garage');
                        self.errorMessage='Erreur lors de la mise à jour du Garage '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeGarage(id){
            GarageService.removeGarage(id)
                .then(
                    function(){
                        console.log('Garage '+id + ' supprimé');
                    },
                    function(errResponse){
                        console.error('Erreur lors de la suppression du Garage '+id +', Erreur :'+errResponse.data);
                    }
                );
        }


        function getAllGarages(){
            return GarageService.getAllGarages();
        }

        function editGarage(id) {
            self.successMessage='';
            self.errorMessage='';
            GarageService.getGarage(id).then(
                function (garage) {
                    self.garage = garage;
                },
                function (errResponse) {
                    console.error('Erreur lors de la suppression du garage ' + id + ', Erreur :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.garage={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);