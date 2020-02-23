const Generator = require('yeoman-generator')
const mkdirp = require('mkdirp')
const glob = require('glob')

module.exports = class extends Generator {
  constructor(args, options) {
    super(args, options)
  }

  async prompting() {
    const defaultArtifact = 'com.example.demo'

    const { artifact, demo } = await this.prompt([
      {
        type: 'input',
        name: 'artifact',
        message: `Artifact:`,
        default: defaultArtifact,
        validate: input => Boolean(input.length),
      },
      {
        type: 'confirm',
        name: 'demo',
        message: 'Create demo use case?',
        default: false,
      },
    ])

    const [domain, host, project] = artifact.split('.')
    this.answers = {
      artifact,
      demo,
      domain,
      host,
      project,
    }
    this.destinationRoot(this.answers.project)
  }

  writing() {
    this.log('Copying templates...')
    const { artifact, demo, domain, host, project } = this.answers
    const className = `${project.charAt(0).toUpperCase()}${project.slice(1)}`

    // Copy all statics.
    this.fs.copy(this.templatePath('static/**/*'), this.destinationPath(), {
      globOptions: { dot: true },
    })

    // Create folders.
    mkdirp.sync(
      `${this.destinationPath()}/src/main/java/${domain}/${host}/${project}/rest`,
    )
    mkdirp.sync(
      `${this.destinationPath()}/src/main/java/${domain}/${host}/${project}/core`,
    )
    mkdirp.sync(
      `${this.destinationPath()}/src/test/java/${domain}/${host}/${project}`,
    )

    // POM.
    this.fs.copyTpl(
      this.templatePath('pom.xml'),
      this.destinationPath('pom.xml'),
      {
        domain,
        host,
        project,
      },
    )

    // README.
    this.fs.copyTpl(
      this.templatePath('README.md'),
      this.destinationPath('README.md'),
      { project },
    )

    // Docker.
    this.fs.copyTpl(
      this.templatePath('Dockerfile'),
      this.destinationPath('Dockerfile'),
      { project },
    )
    this.fs.copyTpl(
      this.templatePath('docker-compose.yml'),
      this.destinationPath('docker-compose.yml'),
      { project },
    )

    // Profiles.
    this.fs.copyTpl(
      this.templatePath('application.yml'),
      this.destinationPath('src/main/resources/application.yml'),
      { project },
    )
    this.fs.copyTpl(
      this.templatePath('application-dev.yml'),
      this.destinationPath('src/main/resources/application-dev.yml'),
      { project },
    )

    // License.
    this.fs.copyTpl(
      this.templatePath('LICENSE'),
      this.destinationPath('LICENSE'),
      { year: new Date().getFullYear() },
    )

    // Code.
    this.fs.copyTpl(
      this.templatePath('Application.java'),
      this.destinationPath(
        `src/main/java/${domain}/${host}/${project}/${className}Application.java`,
      ),
      { artifact, domain, host, project, className },
    )
    if (demo) {
      this.fs.copyTpl(
        this.templatePath('core/**'),
        this.destinationPath(`src/main/java/${domain}/${host}/${project}/core`),
        { artifact, domain, host, project, className },
        {
          globOptions: { dot: true },
        },
      )
      this.fs.copyTpl(
        this.templatePath('rest/**'),
        this.destinationPath(`src/main/java/${domain}/${host}/${project}/rest`),
        { artifact, domain, host, project, className },
        {
          globOptions: { dot: true },
        },
      )
    }
    this.fs.copyTpl(
      this.templatePath('V1__Initialize.sql'),
      this.destinationPath(
        'src/main/resources/db/migration/V1__Initialize.sql',
      ),
      { demo },
    )
  }

  end() {
    this.log('Setting up git...')
    this.spawnCommandSync('git', ['init'])
    this.spawnCommandSync('git', ['checkout', '-b', 'develop'])
    this.spawnCommandSync('git', ['add', '.'])
    this.spawnCommandSync('git', ['commit', '-am', '"Initialize"'])
  }
}
