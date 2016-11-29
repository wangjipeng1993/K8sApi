package com.jp.apiclient.test;
import com.jp.k8s.apiclient.imp.K8sRestfulClient;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
//import io.fabric8.kubernetes.client.dsl.LogWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-11-22.
 */
public class test {
    public static void main(String[] args){
        String master = "http://localhost:8087/";
        Config config = new ConfigBuilder().withMasterUrl(master).build();
        KubernetesClient client = new DefaultKubernetesClient(config);

//        client.replicationControllers().inNamespace("default").withName("rcnginx").scale(1);

//        K8sRestfulClient k8s = new K8sRestfulClient();
//        k8s.RcScala("default", "rcnginx", 0);
//
        //delete service
//        K8sRestfulClient k8s =  new K8sRestfulClient();
//        k8s.deleteService("default", "nginx");



//        // delete rc
//        K8sRestfulClient k8s =  new K8sRestfulClient();
//        k8s.deleteRc("testdefault", "rcnginx1");


        // delete pod
//        client.pods().inNamespace("default").withName("nginx12").delete();
//        client.pods().inNamespace("default").delete();
//        client.close();
//        K8sRestfulClient k8s = new K8sRestfulClient();
//        k8s.deletePod("defauaalt", "nginx");




        // delete namespaces
//        client.namespaces().withName("testdefault").delete();
//        client.close();
//        K8sRestfulClient k8s = new K8sRestfulClient();
//        k8s.deleteNamespace("testdefault1");



        // get service
//        List servicelist = client.services().inNamespace("default").list().getItems();
//        String name = "name=nginx,";
//        for(int i=0; i< servicelist.size(); i++){
//            if(servicelist.get(i).toString().split("selfLink=/api/v1/")[0].contains(name)) {
//                System.out.println("----------------------" + servicelist.get(i));
//            }
//        }
//        client.close();
//        K8sRestfulClient k8s = new K8sRestfulClient();
//        List list = k8s.getservice("testdefault", "name=nginx12,");
//        for(int i=0; i<list.size(); i++){
////            if(list.get(i).toString().split("selfLink=/api/v1/")[0].contains(name)) {
//                System.out.println("---------------------------" + list.get(i));
//            }


        // get rc
//        List rcList = client.replicationControllers().inNamespace("testdefault").list().getItems();
//        String name = "name=rcnginx,";
//        for(int i=0; i<rcList.size(); i++){
//            if(rcList.get(i).toString().split("selfLink=/api/v1/")[0].contains(name)) {
//                System.out.println("---------------------------" + rcList.get(i));
//            }
//        }
//        client.close();
//        K8sRestfulClient k8s = new K8sRestfulClient();
//        List list = k8s.getRC("testdefault", "name=rcnginx1,");
//        for(int i=0; i<list.size(); i++){
//            System.out.println("---------------------------" + list.get(i));
//        }


        //get pod
//        String name = "name=nginx,";
//        List podlist = client.pods().inNamespace("testdefault").list().getItems();
//        for(int i=0; i<podlist.size(); i++){
//            if(podlist.get(i).toString().split("selfLink=/api/v1/")[0].contains(name)) {
//                System.out.println("------------------  " + podlist.get(i));
//            }
//        }
//        K8sRestfulClient k8s = new K8sRestfulClient();
//        List list = k8s.getPod("testdefault", null);
//        for(int i=0; i<list.size(); i++){
//            System.out.println("-------------------------   " + list.get(i));
//        }




        // get namespace
//        List namespace_list = client.namespaces().list().getItems();
//        String name = "name=testsdefault,";
//        for(int i=0; i< namespace_list.size(); i++){
//            if(namespace_list.get(i).toString().split("selfLink=/api/v1/")[0].contains(name)) {
//                System.out.println("---------------------" + namespace_list.get(i));
//            }
//        }
//        client.close();
//        K8sRestfulClient k8s = new K8sRestfulClient();
//        List list = k8s.getNamespace("afsfa");
////        System.out.println("-----------------" + list.size());
//        for(int i=0; i < list.size(); i++){
//            System.out.println("------------------" + list.get(i));
//        }


        // create service
//        Service service = new Service();
//        service.setApiVersion(Service.ApiVersion.V_1);
//        service.setKind("Service");
//        ObjectMeta objectMeta = new ObjectMeta();
//        objectMeta.setName("servicejava");
//        Map<String, String> metadata_labels = new HashMap<String, String>();  // metadata.labels
//        metadata_labels.put("labels","nginx");
//        objectMeta.setLabels(metadata_labels);
//        service.setMetadata(objectMeta);
//        ServiceSpec serviceSpec = new ServiceSpec();
//        Map<String, String> spec_selector = new HashMap<String, String>();  // spec.selector
//        spec_selector.put("selector", "nginx");
//        serviceSpec.setSelector(spec_selector);
//        serviceSpec.setType("ClusterIP");  // spec.type
//        serviceSpec.setClusterIP("string");  // spec.clusterip
//        List<ServicePort> portList = new ArrayList<ServicePort>();  // spec.ports
//        ServicePort servicePort = new ServicePort();
//        servicePort.setName("serviceport");
//        servicePort.setPort(80);
//        IntOrString intOrString = new IntOrString();
//        intOrString.setIntVal(80);
//        servicePort.setTargetPort(intOrString);
//        servicePort.setProtocol("TCP");
//        portList.add(servicePort);
//        serviceSpec.setPorts(portList);
//        service.setSpec(serviceSpec);
//        ServiceStatus serviceStatus = new ServiceStatus();  // sepc.status
//        List<LoadBalancerIngress>  loadBalancerIngresses_list = new ArrayList<LoadBalancerIngress>();  //
//        LoadBalancerStatus loadBalancerStatus = new LoadBalancerStatus();
//        LoadBalancerIngress loadBalancerIngress = new LoadBalancerIngress();
//        loadBalancerIngress.setIp("string");
//        loadBalancerIngress.setHostname("hostname");
//        loadBalancerIngresses_list.add(loadBalancerIngress);
//        loadBalancerStatus.setIngress(loadBalancerIngresses_list);
//        serviceStatus.setLoadBalancer(loadBalancerStatus);
//        service.setStatus(serviceStatus);
//        client.services().inNamespace("default").create(service);
//        client.close();

//        public void createService(String metadata_name, String metadata_namespace, Map<String, String> metadata_labels, Map<String, String> spec_selector,
//                String spec_type, String spec_clusterIP, List<ServicePort> portList, String status_balancer_ingress_ip,
//                String status_balancer_ingress_hostname);

//        K8sRestfulClient k8s = new K8sRestfulClient();
//        k8s.createService("servicejava", "default", metadata_labels, spec_selector, null, null, portList, null, null);


        // create namespaces
//        Namespace ns = new Namespace();
//        ns.setApiVersion(Namespace.ApiVersion.V_1);
//        ns.setKind("Namespace");
//        ObjectMeta om = new ObjectMeta();
//        om.setName("testdefault");
//        ns.setMetadata(om);
//        client.namespaces().create(ns);
//        client.close();
//        System.out.println("1111111111111");
//        K8sRestfulClient k8s = new K8sRestfulClient();
//        k8s.createNamespace("testdefault2");


        // create pod
        Pod pod =new Pod();
        pod.setApiVersion(Pod.ApiVersion.V_1);
        pod.setKind("Pod");
        ObjectMeta om = new ObjectMeta(); //metadata.labels
        om.setName("javapod");
        Map<String , String> metadata_labels = new HashMap<String, String>();
        metadata_labels.put("abc", "abcd");
        om.setLabels(metadata_labels);
        pod.setMetadata(om);

        PodSpec podSpec = new PodSpec();
        List<Container> Container_list = new ArrayList<Container>();
        Container container = new Container();
        container.setName("javanginx");  //spec.containers.name
        container.setImage("index.alauda.cn/kiwenlau/nginx:1.9.7");  // spec.contianers.images
        container.setImagePullPolicy("Always"); // spec.containers.imagepullPolicy  (Always(default, IfNotPresent, Never))

        List<String> command_list = new ArrayList<String>(); // spec.containers.command
//        command_list.add("echo hello !!");
        container.setCommand(command_list);
        container.setWorkingDir("/usr/local"); // spec.containers.workingDir
        List<VolumeMount> volumeMount_list = new ArrayList<VolumeMount>();
        VolumeMount volumeMount = new VolumeMount(); // spec.contianers.volumeMounts
        volumeMount.setName("volumename");
        volumeMount.setMountPath("/usr/local");
        volumeMount.setReadOnly(true);
        volumeMount_list.add(volumeMount);
        container.setVolumeMounts(volumeMount_list);
        List<ContainerPort> containerPorts_list = new ArrayList<ContainerPort>(); // spec.containers.ports
        ContainerPort containerPort = new ContainerPort();
        containerPort.setName("containerports");
        containerPort.setContainerPort(80);
        containerPort.setProtocol("TCP");  // TCP(default)  UDP
        containerPorts_list.add(containerPort);
        container.setPorts(containerPorts_list);
        List<EnvVar> envVar_list = new ArrayList<EnvVar>();  // spec.containers.env
        EnvVar envVar = new EnvVar();
        envVar.setName("aaaa");
        envVar.setValue("bbbb");
        envVar_list.add(envVar);
        container.setEnv(envVar_list);

//        ResourceRequirements resourceRequirements = new ResourceRequirements();  // spec.contaiers.resources
//       Map<String, Quantity> limit_map = new HashMap<String, Quantity>();
//        Quantity quantity = new Quantity();
//        quantity.setAmount("0.5");
//        quantity.setFormat("128Mi");
//        limit_map.put("limits", quantity);
//        resourceRequirements.setLimits(limit_map);
//        container.setResources(resourceRequirements);



        ResourceRequirements resourceRequirements = new ResourceRequirements();
        Map<String, Quantity> limitmap = new HashMap<String, Quantity>();
        Quantity quantity = new Quantity();
        quantity.setAmount("0.5");
//        quantity.setFormat("4Mi");
        limitmap.put("cpu", quantity);

        Quantity quantity1 = new Quantity();
        quantity1.setAmount("4");
        limitmap.put("memory", quantity1);

//        limitmap.put("memory", quantity);
        resourceRequirements.setLimits(limitmap);
        container.setResources(resourceRequirements);



        Container_list.add(container);
        podSpec.setContainers(Container_list);

        List<Volume> volumes_list = new ArrayList<Volume>();  //spec.volumes
        Volume volume = new Volume();
        volume.setName("volumename");
        HostPathVolumeSource hostPathVolumeSource = new HostPathVolumeSource();
        hostPathVolumeSource.setPath("/usr/local/docker");
        volume.setHostPath(hostPathVolumeSource);
        volumes_list.add(volume);
        podSpec.setVolumes(volumes_list);

        // volumes  nfs
//        List<Volume> volumeList = new ArrayList<Volume>();
//        Volume volume1 = new Volume();
//        volume1.setName("nfs");
//        NFSVolumeSource nfsVolumeSource = new NFSVolumeSource();
//        nfsVolumeSource.setServer("nfs-server.localhost");
//        nfsVolumeSource.setPath("/");
//        volume1.setNfs(nfsVolumeSource);
//        volumeList.add(volume1);
//        podSpec.setVolumes(volumes_list);

        // awsElasticBlockStore
//        List<Volume> volumes =  new ArrayList<Volume>();
//        Volume volume1 = new Volume();
//        AWSElasticBlockStoreVolumeSource awsElasticBlockStoreVolumeSource = new AWSElasticBlockStoreVolumeSource();
//        awsElasticBlockStoreVolumeSource.setFsType("ext4");
//        awsElasticBlockStoreVolumeSource.setVolumeID("aws://<availability-zone>");
//        volume1.setAwsElasticBlockStore(awsElasticBlockStoreVolumeSource);
//        volumes.add(volume1);
//        podSpec.setVolumes(volumes);


        //  gcePersistentDisk
//        List<Volume> volumes = new ArrayList<Volume>();
//        Volume volume1 = new Volume();
//        GCEPersistentDiskVolumeSource gcePersistentDiskVolumeSource = new GCEPersistentDiskVolumeSource();
//        gcePersistentDiskVolumeSource.setFsType("ext4");
//        gcePersistentDiskVolumeSource.setPdName("my-data-disk");
//        volume1.setGcePersistentDisk(gcePersistentDiskVolumeSource);
//        volumes.add(volume1);
//        podSpec.setVolumes(volumes);


        // iscsi
//        List<Volume> volumes = new ArrayList<Volume>();
//        Volume volume1 = new Volume();
//        ISCSIVolumeSource iscsiVolumeSource = new ISCSIVolumeSource();
//        iscsiVolumeSource.set
//        volume1.setIscsi();
//        podSpec.setVolumes();


        podSpec.setRestartPolicy("Always");  // spec.restartPollicy (Always(default), OnFailure, Never)
        podSpec.setDnsPolicy("Default"); // spec.dnsPolicy (Default, ClusterFirst)
        pod.setSpec(podSpec);
        client.pods().inNamespace("default").create(pod);
        client.close();

        // create by k8sApi
//    public void createPod(String metadata_name, String metadata_namespace, Map<String , String> metadata_labels,
//                          String spec_con_name, String spec_con_image, String spec_con_imagePullPolicy,
//                          List<String> spec_con_command, String spec_con_workingDir, List<VolumeMount> spec_con_volumeMount,
//                          List<ContainerPort> spec_con_Ports, List<EnvVar> spec_con_env, List<Volume> spec_volumes,
//                          String spec_restartPolicy, String spec_dnsPolicy){
//        K8sRestfulClient k8s = new K8sRestfulClient();
//        k8s.createPod("newpod1", null, metadata_labels,"nginx", "index.alauda.cn/kiwenlau/nginx:1.9.7",
//                null, command_list, null, volumeMount_list, containerPorts_list, envVar_list, volumes_list, null
//        , null);


        // create rc
//        ReplicationController rc = new ReplicationController();
//        rc.setApiVersion(ReplicationController.ApiVersion.V_1);
//        rc.setKind("ReplicationController");
//        ObjectMeta objectMeta = new ObjectMeta();
//        objectMeta.setName("javarcnginx");
//        Map<String, String> metadata_labels = new HashMap<String, String>(); // metadata.lables
//        metadata_labels.put("iii", "ooo");
//        objectMeta.setLabels(metadata_labels);
//        rc.setMetadata(objectMeta);
//        ReplicationControllerSpec RcSpec = new ReplicationControllerSpec();
//        RcSpec.setReplicas(2);
//        Map<String, String> spec_selector_map = new HashMap<String, String>(); //spec.selector
//        spec_selector_map.put("abc", "abcd");
//        RcSpec.setSelector(spec_selector_map);
//        PodTemplateSpec spec_temp_spec = new PodTemplateSpec();  // spec.template
//        ObjectMeta spec_temp_meta_labels = new ObjectMeta();
//        Map<String, String> temp_labels = new HashMap<String, String>();
//        temp_labels.put("abc", "abcd");
//        spec_temp_meta_labels.setLabels(temp_labels);
//        spec_temp_spec.setMetadata(spec_temp_meta_labels);
//        PodSpec podSpec = new PodSpec();
//        List<Container> containers_list = new ArrayList<Container>();
//        Container container = new Container();
//        container.setName("nginx");  // spec.template.spec.containers.name
//        container.setImage("index.alauda.cn/kiwenlau/nginx:1.9.7"); // spec.template.spec.containers.images
//        container.setImagePullPolicy("Always"); // spec.template.spec.containers.imagePullPolicy
//        container.setWorkingDir("/usr/local"); // spec.template.spec.containers.workDir
//        List<String> command_list = new ArrayList<String>(); // spec.containers.command
//        command_list.add("echo hello !!");
//        container.setCommand(command_list);
//        List<VolumeMount> volumeMount_list = new ArrayList<VolumeMount>();
//        VolumeMount volumeMount = new VolumeMount(); // spec.template.spec.containers.volumeMounts
//        volumeMount.setName("volumename");
//        volumeMount.setMountPath("/usr/local");
//        volumeMount.setReadOnly(true);
//        volumeMount_list.add(volumeMount);
//        container.setVolumeMounts(volumeMount_list);
//        List<ContainerPort> containerPorts_list = new ArrayList<ContainerPort>(); // spec.template.spec.containers.ports
//        ContainerPort containerPort = new ContainerPort();
//        containerPort.setName("containerports");
//        containerPort.setContainerPort(80);
//        containerPort.setProtocol("TCP");  // TCP(default)  UDP
//        containerPorts_list.add(containerPort);
//        container.setPorts(containerPorts_list);
//        List<EnvVar> envVar_list = new ArrayList<EnvVar>();  // spec.template.spec.containers.env
//        EnvVar envVar = new EnvVar();
//        envVar.setName("aaaa");
//        envVar.setValue("bbbb");
//        envVar_list.add(envVar);
//        container.setEnv(envVar_list);
//        ResourceRequirements resourceRequirements = new ResourceRequirements();  // spec.template.spec.containers.resources
//        Map<String, Quantity> limit_map = new HashMap<String, Quantity>();
//        Quantity quantity = new Quantity();
//        quantity.setAmount("0.5");
//        quantity.setFormat("128Mi");
//        limit_map.put("limits", quantity);
//        resourceRequirements.setLimits(limit_map);
////        container.setResources(resourceRequirements);
//        containers_list.add(container);
//        podSpec.setContainers(containers_list);
//        List<Volume> volumes_list = new ArrayList<Volume>();  //spec.volumes
//        Volume volume = new Volume();
//        volume.setName("volumename");
//        HostPathVolumeSource hostPathVolumeSource = new HostPathVolumeSource();
//        hostPathVolumeSource.setPath("/usr/local/docker");
//        volume.setHostPath(hostPathVolumeSource);
//        volumes_list.add(volume);
//        podSpec.setVolumes(volumes_list);
//        podSpec.setRestartPolicy("Always");
//        podSpec.setDnsPolicy("Default");
//        spec_temp_spec.setSpec(podSpec);
//        RcSpec.setTemplate(spec_temp_spec);
//        rc.setSpec(RcSpec);
//        client.replicationControllers().inNamespace("default").create(rc);
//        client.close();

        //create rc by k8sapi
//        K8sRestfulClient k8s = new K8sRestfulClient();
//    public void createRC(String metadata_name, String metadata_namespace,Map<String, String> metadata_labels,
//                         int spec_replicas, Map<String, String> spec_selector_map, Map<String, String> temp_labels,
//                         String spec_con_name, String spec_con_image, String spec_con_imagePullPolicy, String spec_con_workingDir,
//                         List<String> spec_con_command, List<VolumeMount> spec_con_volumeMounts, List<ContainerPort> spec_con_ports,
//                         List<EnvVar> spec_con_env, List<Volume> spec_volumes, String spec_restartPolicy, String spec_dnsPolicy){
//        k8s.createRC("javarcnginx", "testdefault", metadata_labels, 2, spec_selector_map, temp_labels, "nginx","index.alauda.cn/kiwenlau/nginx:1.9.7",
//                null, null, command_list, volumeMount_list, containerPorts_list,envVar_list, volumes_list, null,"Default" );

    }
}
