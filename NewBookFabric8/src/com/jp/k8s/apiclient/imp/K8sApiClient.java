package com.jp.k8s.apiclient.imp;

import io.fabric8.kubernetes.api.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-11-22.
 */
interface K8sApiClient {
    // create
    public void createNamespace(String namespace);  // create namespace
    public void createPod(String metadata_name, String metadata_namespace, Map<String , String> metadata_labels,
                          String spec_con_name, String spec_con_image, String spec_con_imagePullPolicy,
                          List<String> spec_con_command, String spec_con_workingDir, List<VolumeMount> spec_con_volumeMount,
                          List<ContainerPort> spec_con_Ports, List<EnvVar> spec_con_env, List<Volume> spec_volumes,
                          String spec_restartPolicy, String spec_dnsPolicy);
    public void createRC(String metadata_name, String metadata_namespace,Map<String, String> metadata_labels,
                         int spec_replicas, Map<String, String> spec_selector_map, Map<String, String> temp_labels,
                         String spec_con_name, String spec_con_image, String spec_con_imagePullPolicy, String spec_con_workingDir,
                         List<String> spec_con_command, List<VolumeMount> spec_con_volumeMounts, List<ContainerPort> spec_con_ports,
                         List<EnvVar> spec_con_env, List<Volume> spec_volumes, String spec_restartPolicy, String spec_dnsPolicy);
    public void createService(String metadata_name, String metadata_namespace, Map<String, String> metadata_labels, Map<String, String> spec_selector,
                              String spec_type, String spec_clusterIP, List<ServicePort> portList, String status_balancer_ingress_ip,
                              String status_balancer_ingress_hostname);


    // get
    public List getNamespace(String namespaceName);
    public List getPod(String namespace, String podName);
    public List getRC(String namespace, String RcName);
    public List getservice(String namespace, String ServieName);

    //delete
    public void deleteNamespace(String namespaceName);
    public void deletePod(String namespace, String PodName);
    public void deleteRc(String namespace, String RcName);
    public void deleteService(String namespace, String ServiceName);

    // scala
    public void RcScala(String namespace, String RcName, int scalaNum);

}
