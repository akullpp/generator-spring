const Generator = require('yeoman-generator')
const mkdirp = require('mkdirp')

module.exports = class extends Generator {
  constructor(args, options) {
    super(args, options)
  }

  async prompting() {
    const defaultArtifact

    const { artifact } = await this.prompt([
      {
        type: 'input',
        name: 'artifact',
        message: `Artifact (${defaultArtifact}):`,
        default: defaultArtifact,
        validate: input => Boolean(input.length),
      },
    ])
    const [domain, host, project] = artifact.split('.')
    this.answers = {
      domain,
      host,
      project,
    }
    this.destinationRoot(this.answers.project)
  }

  writing() {
    this.log('Copying templates...')
    const { domain, host, project } = this.answers
    const className = `${project.charAt(0).toUpperCase()}${project.slice(1)}`

    this.fs.copy(this.templatePath('static/**/*'), this.destinationPath(), {
      globOptions: { dot: true },
    })

    this.fs.copyTpl(
      this.templatePath('pom.xml'),
      this.destinationPath('pom.xml'),
      {
        domain,
        host,
        project,
      },
    )

    this.fs.copyTpl(
      this.templatePath('README.md'),
      this.destinationPath('README.md'),
      { project },
    )

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

    this.fs.copyTpl(
      this.templatePath('Application.java'),
      this.destinationPath(
        `src/main/java/${domain}/${host}/${project}/${className}Application.java`,
      ),
      { domain, host, project, className },
    )

    this.fs.copyTpl(
      this.templatePath('LICENSE'),
      this.destinationPath('LICENSE'),
      { year: new Date().getFullYear() },
    )

    mkdirp.sync(
      `${this.destinationPath()}/src/main/java/${domain}/${host}/${project}/controller`,
    )
    mkdirp.sync(
      `${this.destinationPath()}/src/main/java/${domain}/${host}/${project}/core/domain`,
    )
    mkdirp.sync(
      `${this.destinationPath()}/src/main/java/${domain}/${host}/${project}/dto`,
    )
    mkdirp.sync(
      `${this.destinationPath()}/src/test/java/${domain}/${host}/${project}`,
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
