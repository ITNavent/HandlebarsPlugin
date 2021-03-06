grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

//para publicar el plugin
grails.project.repos.default = "nexusNavent"
grails.project.repos.nexusNavent.url = "http://nexus.navent.biz:8081/nexus/content/repositories/releases/"
grails.project.repos.nexusNavent.username = "deployment"
grails.project.repos.nexusNavent.password = "n4v3ntd3v"

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
		mavenRepo "http://files.couchbase.com/maven2/"
		mavenRepo "http://repository.codehaus.org/"
		mavenRepo "http://maven.restlet.org/"
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
	
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        // runtime 'mysql:mysql-connector-java:5.1.27'
		
		//MUSTACHE
		compile 'com.github.jknack:handlebars:4.0.6','com.github.jknack:handlebars-helpers:4.0.6', {
			excludes  "rhino"
		}
    }

    plugins {
        build(":release:3.1.1", ":rest-client-builder:2.1.1") {
            export = false
        }
		
		
		
		//necesario para compilar, pero no lo distribuimos
		compile(':asset-pipeline:2.1.5') {
			export = false
		}
		
		//necesario para compilar, pero no lo distribuimos
		//JAWR https://grails.org/plugin/jawr
		provided(":jawr:3.8") {
			excludes "slf4j-log4j12"
			export = false
		}
    }
}
