'use strict';

angular.module('crudApp').controller('VoitureController',
    ['VoitureService', '$scope',  function( VoitureService, $scope) {

        var self = this;
        self.voiture = {};
        self.voitures=[];

        self.submit = submit;
        self.getAllVoitures = getAllVoitures;
        self.createVoiture = createVoiture;
        self.updateVoiture = updateVoiture;
        self.removeVoiture = removeVoiture;
        self.editVoiture = editVoiture;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Validation');
            if (self.voiture.id === undefined || self.voiture.id === null) {
                console.log('Enregistrement du nouveau Voiture', self.voiture);
                createVoiture(self.voiture);
            } else {
                updateVoiture(self.voiture, self.voiture.id);
                console.log('Voiture mis à jour avec l''id ', self.voiture.id);
            }
        }

        function createVoiture(voiture) {
            VoitureService.createVoiture(voiture)
                .then(
                    function (response) {
                        console.log('Voiture créé');
                        self.successMessage = 'Voiture créé';
                        self.errorMessage='';
                        self.done = true;
                        self.voiture={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Erreur lors de la création du Voiture');
                        self.errorMessage = 'Erreur lors de la création du Voiture: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateVoiture(voiture, id){
            VoitureService.updateVoiture(voiture, id)
                .then(
                    function (response){
                        console.log('Voiture mis à jour');
                        self.successMessage='Voiture mis à jour';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Erreur lors de la mise à jour du Voiture');
                        self.errorMessage='Erreur lors de la mise à jour du Voiture '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeVoiture(id){
            VoitureService.removeVoiture(id)
                .then(
                    function(){
                        console.log('Voiture '+id + ' supprimé');
                    },
                    function(errResponse){
                        console.error('Erreur lors de la suppression du Voiture '+id +', Erreur :'+errResponse.data);
                    }
                );
        }


        function getAllVoitures(){
            return VoitureService.getAllVoitures();
        }

        function editVoiture(id) {
            self.successMessage='';
            self.errorMessage='';
            VoitureService.getVoiture(id).then(
                function (voiture) {
                    self.voiture = voiture;
                },
                function (errResponse) {
                    console.error('Erreur lors de la suppression du voiture ' + id + ', Erreur :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.voiture={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);