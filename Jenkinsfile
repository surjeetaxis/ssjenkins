@Library('my-shared-library') _
pipeline{
    agent any
    parameters{
        choice(name: 'action', choices: 'create\ndelete', description: 'Choose create/Destroy')
        string(name: 'aws_account_id', description: "name of aws account id", defaultValue: '093335397012')
        string(name: 'region', description: "name of the region", defaultValue: 'eu-north-1')
        string(name: 'ecr_repo_name', description: "name of the ECR", defaultValue: 'ssjeknins')
    }
    stages{
        stage('Git Checkout'){
            when { expression {  params.action == 'create' } }
            steps{
            gitCheckout(branch: "master",url: "https://github.com/surjeetaxis/ssjenkins.git")
            }
        }
//         stage('Static code analysis: Sonarqube'){
//          when { expression {  params.action == 'create' } }
//             steps{
//                script{
//                    def SonarQubecredentialsId = 'sonarlocal-api'
//                    statiCodeAnalysis(SonarQubecredentialsId)
//                }
//             }
//         }
//         stage('Quality Gate Status Check : Sonarqube'){
//          when { expression {  params.action == 'create' } }
//             steps{
//                script{
//                    def SonarQubecredentialsId = 'sonarlocal-api'
//                    QualityGateStatus(SonarQubecredentialsId)
//                }
//             }
//         }
        stage('Maven Build : maven'){
         when { expression {  params.action == 'create' } }
            steps{
               script{
                   mvnBuild()
               }
            }
        }
        stage('Docker Image Build ECR'){
         when { expression {  params.action == 'create' } }
            steps{
               script{
                   dockerBuild("${params.aws_account_id}","${params.region}","${params.ecr_repo_name}")
               }
            }
        }
//          stage('Docker Image Scan: trivy '){
//          when { expression {  params.action == 'create' } }
//             steps{
//                script{
//                    dockerImageScan("${params.aws_account_id}","${params.region}","${params.ecr_repo_name}")
//                }
//             }
//         }
        stage('Docker Image Push : DockerHub '){
         when { expression {  params.action == 'create' } }
            steps{
               script{
                   dockerImagePush("${params.aws_account_id}","${params.region}","${params.ecr_repo_name}")
               }
            }
        }
        stage('Docker Image Cleanup : DockerHub '){
         when { expression {  params.action == 'create' } }
            steps{
               script{
                   dockerImageCleanup("${params.aws_account_id}","${params.region}","${params.ecr_repo_name}")
               }
            }
        }
    }
}