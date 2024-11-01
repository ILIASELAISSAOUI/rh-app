package com.service_employes.services;

import org.springframework.scheduling.annotation.Scheduled;

public class TeletravailSheduler {

    private DemandeTeletravailService demandeTeletravailService;

    public TeletravailSheduler(DemandeTeletravailService demandeTeletravailService) {
        this.demandeTeletravailService = demandeTeletravailService;
    }

    @Scheduled(cron = "0 0 0 * * ?")  // Exécute tous les jours à minuit
    public void checkAndRejectExpiredDemandes() {
        demandeTeletravailService.rejectExpiredDemandes();
    }
}
