package com.ted.app.lib;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Prescriber implements Runnable {

    private DiseasesHandler handler;

    private final BlockingQueue<PrescriptionDemandInfo> queue;

    public Prescriber(DiseasesHandler handler, BlockingQueue<PrescriptionDemandInfo> queue) {
        this.handler = handler;
        this.queue = queue;
    }

    private Prescription prescriptionDemand(PrescriptionDemandInfo info) throws InterruptedException {
        Thread.sleep(3000);
        return handler.handle(info.getPatient(), info.getSymptoms());
    }

    public void update(List<String> supportedDiseases) {
        DiseasesHandler curHandler = handler;

        allHandlerDisable();

        while (curHandler != null) {

            for (String supportedDisease : supportedDiseases) {

                if (supportedDisease.equals(curHandler.getDisease().getName())) {
                    curHandler.setEnable(true);
                }
            }

            curHandler = curHandler.getNext();
        }
    }

    private void allHandlerDisable() {
        DiseasesHandler curHandler = handler;

        while (curHandler != null) {
            curHandler.setEnable(false);
            curHandler = curHandler.getNext();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                PrescriptionDemandInfo info = queue.take();
                Prescription prescription = prescriptionDemand(info);
                info.getResultFuture().complete(prescription);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //=======================================


    public DiseasesHandler getHandler() {
        return handler;
    }

    public void setHandler(DiseasesHandler handler) {
        this.handler = handler;
    }

    public BlockingQueue<PrescriptionDemandInfo> getQueue() {
        return queue;
    }
}
